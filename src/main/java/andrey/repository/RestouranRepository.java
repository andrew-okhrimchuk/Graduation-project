package andrey.repository;

import andrey.model.Restouran;
import andrey.to.MealMenu;
import andrey.to.RestouranTo;

import java.util.List;

public interface RestouranRepository {

    // null if updated meal do not belong to userId
    Restouran save(Restouran restouran, int user_id);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Restouran get(int id);

    // ORDERED dateTime desc
    List<Restouran> getAll(int userId);

    List<MealMenu> getManuToday();

    default Restouran getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
