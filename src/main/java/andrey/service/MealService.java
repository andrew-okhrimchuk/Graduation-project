package andrey.service;


import andrey.model.Meal;
import andrey.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal get(int id, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    List<Meal> getAll(int userId);

    Meal update(Meal meal, int userId) throws NotFoundException;

    Meal create(Meal meal, int userId);
}