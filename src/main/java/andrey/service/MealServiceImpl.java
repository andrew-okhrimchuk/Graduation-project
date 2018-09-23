package andrey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.model.Meal;
import andrey.repository.MealRepository;

import java.util.List;
import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int restouran_id) {
        return checkNotFoundWithId(repository.get(id, restouran_id), id);
    }

    @Override
    public List<Meal> getAll(int restouran_id) {
        return repository.getAll(restouran_id);
    }

    @Override
    public void delete(int id, int restouran_id) {
        checkNotFoundWithId(repository.delete(id, restouran_id), restouran_id);
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
