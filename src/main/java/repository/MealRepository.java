package repository;


import model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    boolean delete(int id);

    Meal get(int id);

    // ORDERED dateTime desc
    List<Meal> getAll();

    // ORDERED dateTime desc
    List<Meal> getAllToday();

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate);
}
