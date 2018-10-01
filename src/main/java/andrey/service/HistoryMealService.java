package andrey.service;


import andrey.model.HistoryMeal;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealService {

    HistoryMeal get(int historyMeal_id) throws NotFoundException;

    List<HistoryMeal> getByMealId(int id);
    List<HistoryMeal> getByDateBetween(LocalDate start, LocalDate end);
    List<HistoryMeal> getByRestouranId(int id);
    List<HistoryMeal> getByCost(long id);
    List<HistoryMeal> getAll();

    HistoryMeal update(HistoryMeal historyMeal, int meal, int cost, int userId) throws NotFoundException;
    HistoryMeal create(HistoryMeal historyMeal, int meal, int cost, int userId);

    void delete(int historyMeal_id, int user_id);
}