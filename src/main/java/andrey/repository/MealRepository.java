package andrey.repository;
import andrey.model.Meal;
import andrey.to.MealTo;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {

    // null if updated meal do not belong to restouran_id
    Meal save(MealTo meal);

    // false if meal do not belong to restouran_id
    boolean delete(int id);

    // null if meal do not belong to restouran_id
    Meal getWithLastCost(int id);
    Meal getWithDate(int id, LocalDate date);

    // ORDERED dateTime desc
    List<Meal> getAll(int restouran_id);

    default Meal getWithUser(int id, int restouran_id) {
        throw new UnsupportedOperationException();
    }
}
