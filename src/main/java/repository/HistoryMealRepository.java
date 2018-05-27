package repository;

import model.HistoryMeal;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealRepository {

    HistoryMeal save(HistoryMeal historyMeal, int meal, int restouran, int userId);
    List<HistoryMeal> getMealId(int id);
    List<HistoryMeal> getDate(LocalDate date);
    List<HistoryMeal> getRestouranId(int id);
    List<HistoryMeal> getCost(int id);

    // ORDERED dateTime desc
    List<HistoryMeal> getAll();

    default HistoryMeal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
