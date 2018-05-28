package repository;

import model.HistoryMeal;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealRepository {

    HistoryMeal save(HistoryMeal historyMeal, int meal, int restouran, int userId);
    HistoryMeal getId(int id);
    boolean delete(int historyMeal_id, int user_id);
    List<HistoryMeal> getMealId(int id);
    List<HistoryMeal> getByDateBetween(LocalDate start, LocalDate end);
    List<HistoryMeal> getRestouranId(int id);
    List<HistoryMeal> getCost(long id);

    // ORDERED dateTime desc
    List<HistoryMeal> getAll();

    default HistoryMeal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
