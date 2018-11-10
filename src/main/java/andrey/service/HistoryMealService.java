package andrey.service;


import andrey.model.HistoryMeal;
import andrey.model.Meal;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealService {

    HistoryMeal get(int Meal_id) throws NotFoundException;
    List<HistoryMeal> getAll();

    HistoryMeal update(HistoryMeal hm, Meal meal, long cost) throws NotFoundException;
    HistoryMeal create(HistoryMeal hm, Meal meal, long cost);
}