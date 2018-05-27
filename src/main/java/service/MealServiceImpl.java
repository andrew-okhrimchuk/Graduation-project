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

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return (repository.delete(id, userId));
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
