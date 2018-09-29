package andrey.repository;

import andrey.model.HistoryMeal;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealRepository {

    HistoryMeal save(HistoryMeal historyMeal, int meal_Id, long cost, LocalDate date, int userId);
    boolean delete(int historyMeal_id, int user_id);

    HistoryMeal getId(int id);


    List<HistoryMeal> getByMealId(int id);
    List<HistoryMeal> getByDateBetween(LocalDate start, LocalDate end);
    List<HistoryMeal> getRestouranId(int id);
    List<HistoryMeal> getCost(long cost);

    // ORDERED dateTime desc
    List<HistoryMeal> getAll();
    List<HistoryMeal> isMealBelongRestouran (int historyMeal_id, List<Integer> r_id);

    default HistoryMeal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
