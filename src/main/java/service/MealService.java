package service;


import model.Meal;
import util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal get(int id);

    List<Meal> getAllMealToday();

    List<Meal> getAll();

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Meal> getAllMealOfRestouran(int restouranId);

    boolean delete(int id);

    boolean deleteAll();

    Meal update(Meal meal, int restouranId);

    Meal create(Meal meal, int restouranId);

}