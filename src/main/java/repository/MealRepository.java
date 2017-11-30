package repository;


import model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int restouranId);

    boolean delete(int id);
    boolean deleteAll();

    Meal get(int id);

    // ORDERED dateTime desc
    List<Meal> getAll();

    // ORDERED dateTime desc
    List<Meal> getAllMealToday();

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate);

    public List<Meal> getAllMealOfRestouran(int restouranId);
}
