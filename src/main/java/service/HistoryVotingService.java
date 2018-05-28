package service;


import model.HistoryVoting;
import util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    HistoryVoting get(int historyVoting_id) throws NotFoundException;

    List<HistoryVoting> getByDateBetween(LocalDateTime start, LocalDateTime end);
    List<HistoryVoting> getByRestouranId(int id);
    List<HistoryVoting> getByUserId(int id);
    List<HistoryVoting> getAll();

    HistoryVoting update(HistoryVoting historyVoting, int userId) throws NotFoundException;
    HistoryVoting create(HistoryVoting historyVoting, int restouran, int userId);

}