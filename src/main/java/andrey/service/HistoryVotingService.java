package andrey.service;


import andrey.model.HistoryVoting;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    HistoryVoting getToday() throws NotFoundException;
    HistoryVoting getVotingTodayByUser(int Id);
    List<HistoryVoting> getByDateBetween(LocalDateTime start, LocalDateTime end);
    HistoryVoting getByDate(LocalDate date);
    List<HistoryVoting> getByRestouranId(int id);
    List<HistoryVoting> getByUserId(int id);
    List<HistoryVoting> getAll();

    HistoryVoting save_a_vote(int restouran, int userId);

}