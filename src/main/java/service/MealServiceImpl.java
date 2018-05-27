/*
package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import model.Meal;
import repository.MealRepository;
import java.time.LocalDateTime;
import java.util.List;
import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;
    private final LocalDateTime MIN = LocalDateTime.MIN;
    private final LocalDateTime MAX = LocalDateTime.MAX;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }
    @Override
    public List<Meal> getAllMealToday(){
        LocalDateTime now = LocalDateTime.now();
        return repository.getBetweenAllMealOfRestouran(now, now, 0);
    }
    @Override
    public List<Meal> getAll() {
        return repository.getBetweenAllMealOfRestouran(null, null, 0);
    }
    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getBetweenAllMealOfRestouran(startDateTime, endDateTime, 0);
    }
    @Override
    public List<Meal> getAllMealOfRestouran(int restouranId) {
        return repository.getBetweenAllMealOfRestouran(MIN, MAX, restouranId);
    }
    @Override
    public boolean delete(int id) {
        return (repository.delete(id));
    }
    @Override
    public boolean deleteAll() {
        return repository.deleteAll();
    }
    @Override
    public Meal update(Meal meal, int restouranId) {
        return checkNotFoundWithId(repository.save(meal, restouranId), meal.getId());
    }
    @Override
    public Meal create(Meal meal, int restouranId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restouranId);
    }



}
*/
