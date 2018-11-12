package andrey.service;


import andrey.model.Meal;
import andrey.to.MealTo;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    Meal get(int id, LocalDate date) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAll(int restouran_id);

    MealTo update(MealTo meal) throws NotFoundException;

    MealTo create(MealTo meal);
}