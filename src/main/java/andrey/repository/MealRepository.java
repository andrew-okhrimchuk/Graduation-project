package andrey.repository;
import andrey.model.Meal;

import java.util.List;

public interface MealRepository {

    // null if updated meal do not belong to restouran_id
    Meal save(Meal meal);

    // false if meal do not belong to restouran_id
    boolean delete(int id);

    // null if meal do not belong to restouran_id
    Meal get(int id);

    // ORDERED dateTime desc
    List<Meal> getAll(int restouran_id);

    default Meal getWithUser(int id, int restouran_id) {
        throw new UnsupportedOperationException();
    }
}
