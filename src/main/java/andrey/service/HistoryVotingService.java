package andrey.service;


import andrey.model.HistoryVoting;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    List<HistoryVoting>  getToday() throws NotFoundException;
    List<HistoryVoting>  getByDate(LocalDate date);
    List<HistoryVoting> getAll();
    HistoryVoting save_a_vote(int restouran, int userId);

    HistoryVoting getVotingTodayByUser(int Id);
    List<HistoryVoting> getByDateBetween(LocalDate start, LocalDate end);
    List<HistoryVoting> getByRestouranId(int id);
    List<HistoryVoting> getByUserId(int id);


}