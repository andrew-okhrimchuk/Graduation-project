package andrey.repository;

import andrey.model.HistoryMeal;
import andrey.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealRepository {

    HistoryMeal save(HistoryMeal historyMeal, Meal meal, long cost);

    HistoryMeal getMealId(int id);
    List<HistoryMeal> getAll();
}
