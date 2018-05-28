package service;

import model.HistoryVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.HistoryVotingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

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
    public List<HistoryVoting> getByUserId(int user_id){
        Assert.notNull(user_id, "user_id must not be null");
        return repository.getUser(user_id);
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
        return repository.getRestouranId(id);}

    @Override
    public HistoryVoting update(HistoryVoting historyVoting,  int userId) {
        Assert.notNull(historyVoting, "HistoryVoting(u) must not be null");
        return checkNotFoundWithId(repository.save(historyVoting,  -1,  userId), historyVoting.getId());}

    @Override
    public HistoryVoting create(HistoryVoting historyVoting, int restouran, int userId) {
        Assert.notNull(historyVoting, "HistoryVoting(c) must not be null");
        return repository.save(historyVoting,  restouran,  userId);
    }
}
