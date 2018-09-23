package andrey.service;


import andrey.model.Meal;
import andrey.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal get(int id, int restouran_id) throws NotFoundException;

    void delete(int id, int restouran_id) throws NotFoundException;

    List<Meal> getAll(int restouran_id);

    Meal update(Meal meal, int restouran_id) throws NotFoundException;

    Meal create(Meal meal, int restouran_id);
}