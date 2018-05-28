package repository;

import model.HistoryVoting;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingRepository {

    HistoryVoting save(HistoryVoting historyVoting, int restouran, int userId);
    HistoryVoting get(int id);
    HistoryVoting getVotingToday(int userId);
    List<HistoryVoting> getByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<HistoryVoting> getRestouranId(int restouranId);
    List<HistoryVoting> getUser(int userId);

    // ORDERED dateTime desc
    List<HistoryVoting> getAll();

    default HistoryVoting getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
