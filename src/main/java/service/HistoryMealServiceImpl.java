package service;

import model.HistoryMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.HistoryMealRepository;

import java.time.LocalDate;
import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class HistoryMealServiceImpl implements HistoryMealService {

    private final HistoryMealRepository repository;

    @Autowired
    public HistoryMealServiceImpl(HistoryMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HistoryMeal> getByMealId(int id){
        Assert.notNull(id, "meal_id must not be null");
        return repository.getMealId(id);
    }

    @Override
    public HistoryMeal get(int historyMeal_id) {
        return checkNotFoundWithId(repository.getId(historyMeal_id), historyMeal_id);
    }

    @Override
    public List<HistoryMeal> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int historyMeal_id, int user_id) {
        checkNotFoundWithId (repository.delete(historyMeal_id, user_id), historyMeal_id);
    }

    @Override
    public List<HistoryMeal> getByDateBetween(LocalDate startDateTime, LocalDate endDateTime){
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getByDateBetween(startDateTime, endDateTime);}

    @Override
    public List<HistoryMeal> getByRestouranId(int id){
        Assert.notNull(id, "RestouranId must not be null");
        return repository.getRestouranId(id);}

    @Override
    public List<HistoryMeal> getByCost(long id){
        Assert.notNull(id, "Cost must not be null");
        return repository.getCost(id);}

    @Override
    public HistoryMeal update(HistoryMeal historyMeal, int meal, int restouran, int userId) {
        Assert.notNull(historyMeal, "HistoryMeal(u) must not be null");
        return checkNotFoundWithId(repository.save(historyMeal,  meal, restouran,  userId), historyMeal.getId());}

    @Override
    public HistoryMeal create(HistoryMeal historyMeal, int meal, int restouran, int userId) {
        Assert.notNull(historyMeal, "HistoryMeal(c) must not be null");
        return repository.save(historyMeal,  meal, restouran,  userId);
    }
}
