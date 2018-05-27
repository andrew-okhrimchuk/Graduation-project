package service;


import model.HistoryMeal;
import util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMealService {

    HistoryMeal get(int historyMeal_id) throws NotFoundException;

    List<HistoryMeal> getByMealId(int id);
    List<HistoryMeal> getByDate(LocalDate date);
    List<HistoryMeal> getByRestouranId(int id);
    List<HistoryMeal> getByCost(long id);
    List<HistoryMeal> getAll();

    HistoryMeal update(HistoryMeal historyMeal, int meal, int restouran, int userId) throws NotFoundException;
    HistoryMeal create(HistoryMeal historyMeal, int meal, int restouran, int userId);

    boolean delete(int historyMeal_id, int user_id);
}