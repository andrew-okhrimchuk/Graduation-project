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
    public HistoryVoting getToday() {
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        List<HistoryVoting> resultList = repository.getByDateBetween(startDateTime, endDateTime);
        checkNotFoundList (resultList, "Вы не голосовали сегодня.");
        return resultList.get(0);
    }

    @Override
    public HistoryVoting getByDate(LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.MAX);
        List<HistoryVoting> resultList = repository.getByDateBetween(startDateTime, endDateTime);
        checkNotFoundList (resultList, "Вы не голосовали эа выбранную дату.");
        return resultList.get(0);
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
    public List<HistoryVoting> getByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime){
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getByDateBetween(startDateTime, endDateTime);}

    @Override
    public List<HistoryVoting> getByRestouranId(int id){
        Assert.notNull(id, "Restouran_id must not be null");
        return repository.getByRestouranId(id);}


    @Override
    public HistoryVoting save_a_vote(int restouran, int userId) {
        LocalDate dateOfVoting = threadLocalUtil.getThreadLocalScope();
        //проверка: голосовал ли юсер сегодня или нет. Если нет  - просто Save. Если да - ветка.

        if(dateOfVoting == LocalDate.now()) {

        }



            threadLocalUtil.setThreadLocalScope(LocalDate.now());
            return repository.save(new HistoryVoting(),  restouran,  userId);
    }


    //2
    public HistoryVoting update(HistoryVoting historyVoting, int userId) {
        return repository.save(historyVoting, 0, userId);
    }




}
