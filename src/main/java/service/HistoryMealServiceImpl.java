package service;

import model.HistoryMeal;
import model.Restouran;
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
    public boolean delete(int historyMeal_id, int user_id) {
            return repository.delete(historyMeal_id, user_id);
    }

    @Override
    public List<HistoryMeal> getByDate(LocalDate date){ return repository.getDate(date);}

    @Override
    public List<HistoryMeal> getByRestouranId(int id){return repository.getRestouranId(id);}

    @Override
    public List<HistoryMeal> getByCost(long id){return repository.getCost(id);}

    @Override
    public HistoryMeal update(HistoryMeal historyMeal, int meal, int restouran, int userId) {
        return checkNotFoundWithId(repository.save(historyMeal,  meal, restouran,  userId), historyMeal.getId());}

    @Override
    public HistoryMeal create(HistoryMeal historyMeal, int meal, int restouran, int userId) {
        Assert.notNull(historyMeal, "historyMeal must not be null");
        return repository.save(historyMeal,  meal, restouran,  userId);
    }
}
