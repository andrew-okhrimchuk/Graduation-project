package andrey.repository;

import andrey.model.HistoryVoting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingRepository {

    HistoryVoting save(HistoryVoting historyVoting, int restouran, int userId);

    HistoryVoting getVotingTodayByUser(int Id);
    List<HistoryVoting> getByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<HistoryVoting> getByRestouranId(int Id);
    List<HistoryVoting> getByUser(int Id);

    // ORDERED dateTime desc
    List<HistoryVoting> getAll();

    default HistoryVoting getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
