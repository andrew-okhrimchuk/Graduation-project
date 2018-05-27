package repository;

import model.Restouran;

import java.time.LocalDateTime;
import java.util.List;

public interface RestouranRepository {

    // null if updated meal do not belong to userId
    Restouran save(Restouran restouran, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Restouran get(int id, int userId);

    // ORDERED dateTime desc
    List<Restouran> getAll(int userId);

    default Restouran getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
