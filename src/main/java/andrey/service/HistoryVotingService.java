package andrey.service;


import andrey.model.HistoryVoting;
import andrey.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    HistoryVoting get(int historyVoting_id) throws NotFoundException;
    HistoryVoting getVotingTodayByUser(int Id);
    List<HistoryVoting> getByDateBetween(LocalDateTime start, LocalDateTime end);
    List<HistoryVoting> getByRestouranId(int id);
    List<HistoryVoting> getByUserId(int id);
    List<HistoryVoting> getAll();

    HistoryVoting save_a_vote(HistoryVoting historyVoting, int restouran, int userId);

}