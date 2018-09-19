package andrey.service;

import andrey.model.HistoryVoting;
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

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingRepository repository) {
        this.repository = repository;
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
}
