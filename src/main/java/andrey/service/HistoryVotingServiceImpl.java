package andrey.service;

import andrey.model.HistoryVoting;
import andrey.util.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.repository.HistoryVotingRepository;
import andrey.util.exception.AlreadyVotedException;

import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static andrey.util.ValidationUtil.checkNotFound;
import static andrey.util.ValidationUtil.checkNotFoundList;
import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    public static String massageMore11AM = "Время больше 11 утра. Голосование окончено!";
    private final HistoryVotingRepository repository;
    private ThreadLocalUtil threadLocalUtil;

    private static RestouranServiceImpl.Expression checkUserIsAdminOfList = (list, user_id, rest_id)-> list.getUser().getId()==user_id;
    private static RestouranServiceImpl.Expression checkUserIsAdminOfRestouran = (list, user_id, rest_id)-> list.getRestouran().getId()==rest_id;
    private static String messegecheckUserRoles = " User is not admin of restouran ";
    private static String messegecheckUserIsAdminOfRestouran = " User is not admin of restouran ";

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingRepository repository, ThreadLocalUtil threadLocalUtil) {
        this.repository = repository;
        this.threadLocalUtil = threadLocalUtil;
    }

    @Override
    public List<HistoryVoting>  getToday() {
        LocalDate dateTime = LocalDate.now();
        List<HistoryVoting> resultList = repository.getByDateBetween(dateTime, dateTime);
        checkNotFoundList (resultList, "Вы не голосовали сегодня.");
        return resultList;
    }

    @Override
    public List<HistoryVoting>  getByDate(LocalDate date) {
        List<HistoryVoting> resultList = repository.getByDateBetween(date, date);
        checkNotFoundList (resultList, "Вы не голосовали эа выбранную дату.");
        return resultList;
}

    @Override
    public HistoryVoting getVotingTodayByUser(int user_id) {
        return checkNotFoundWithId(repository.getVotingTodayByUser(user_id), user_id);
    }
    @Override
    public List<HistoryVoting> getByUserId(int user_id){
        Assert.notNull(user_id, "user_id must not be null");
        return repository.getByUser(user_id);
    }

    @Override
    public List<HistoryVoting> getAll() {
        return repository.getAll();
    }

    @Override
    public List<HistoryVoting> getByDateBetween(LocalDate startDate, LocalDate endDate){
        Assert.notNull(startDate, "startDateTime must not be null");
        Assert.notNull(endDate, "endDateTime  must not be null");
        return repository.getByDateBetween(startDate, endDate);}

    @Override
    public List<HistoryVoting> getByRestouranId(int id){
        Assert.notNull(id, "Restouran_id must not be null");
        return repository.getByRestouranId(id);}


    @Override
    public HistoryVoting save_a_vote(int restouran, int userId) {
        HistoryVoting historyVoting = threadLocalUtil.getThread_HV();
        //Проверка голосования до 11 АМ
        if (LocalTime.now().getHour() >= LocalTime.of(11, 00).getHour()
         && LocalTime.now().getMinute() >= LocalTime.of(11, 00).getMinute()) { throw new AlreadyVotedException(massageMore11AM) ;}

        //проверка: голосовал ли юсер сегодня или нет. Если нет  - просто Save. Если да - ветка.
        //юсер не голосовал сегодня: historyVoting == null
        if(historyVoting == null) {
            HistoryVoting newHV = new HistoryVoting();
            newHV.setDateTime(LocalDate.now());
            HistoryVoting saved = repository.save(newHV,  restouran,  userId);
            checkNotFound(saved, "Не удачное сохранение голосования");
            threadLocalUtil.setThread_HV(saved);
            return saved ;
        }
        //юсер голосовал сегодня: historyVoting != null и isSecondVotin() == false
        if(historyVoting.isSecondVotin() == false) {
            historyVoting.setSecondVotin(true);
            HistoryVoting saved = repository.save(historyVoting,  restouran,  userId);
            checkNotFound(saved, "Не удачное сохранение повторного голосования");
            threadLocalUtil.setThread_HV(saved);
            return saved ;
        }
        return historyVoting;





    }


    //2
    public HistoryVoting update(HistoryVoting historyVoting, int userId) {
        return repository.save(historyVoting, 0, userId);
    }




}
