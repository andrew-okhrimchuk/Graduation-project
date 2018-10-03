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
import java.time.LocalDateTime;
import java.util.List;

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
    public HistoryVoting get(int historyVoting_id) {
        return checkNotFoundWithId(repository.get(historyVoting_id), historyVoting_id);
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
    public HistoryVoting save_a_vote(HistoryVoting historyVoting, int restouran, int userId) {
        Assert.notNull(historyVoting, "HistoryVoting(c) must not be null");

       /* User user = (User)tl.get();
        if (user.isVoting()) {
            return null;
        }*/

       //1 проверка на ИД юсера залогиненого и сделать в Raise_in_ThreadLocal добавление Юсера в Авторизованного пользователя!!!
        // потом взять его из Секьюрити и сделать проверку
        //isUserLogged();
        // 2 проверка на право голосования,
        // убрать все переменные из сервиса!!!! если есть конечно.

        return repository.save(historyVoting,  restouran,  userId);
    }


    //2
    public HistoryVoting update(HistoryVoting historyVoting, int userId) {
        return repository.save(historyVoting, 0, userId);
    }



        //3
    private HistoryVoting already_voted (int userId) {
        throw new AlreadyVotedException("User id = " + userId + "already voted");
    }

    /*public  void isUserLogged(int userId, String messege, RestouranServiceImpl.Expression func, int rest_id) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        boolean isOk = false;
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        if (list_of_admin == null) {
            throw  new NotEnoughRightsException("User id = " + userId + messege + ". User is not admin!");
        }
        else {
            for (List_of_admin admin : list_of_admin) {
                if (func.isEqual(admin,userId,rest_id)) {
                    isOk = true;
                }
            }
        }

        if (!isOk) {throw new NotEnoughRightsException("User id = " + userId + messege);
        }


    interface Expression{
        boolean isEqual(List_of_admin list, int user_id, int rest_id);
    }}*/
}
