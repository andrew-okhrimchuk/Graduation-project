package andrey.service;

import andrey.repository.RestouranRepository;
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
    private final RestouranRepository res_repository;

    @Autowired
    public MealServiceImpl(MealRepository repository, RestouranRepository res_repository) {
        this.repository = repository;
        this.res_repository = res_repository;
    }

    @Override
    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Meal> getAll(int restouran_id) {
        return repository.getAll(restouran_id);
    }

    @Override
    public void delete(int id, int restouranId) {
        checkNotFoundWithId(res_repository.get(restouranId), restouranId);
        checkNotFoundWithId(repository.delete(id, restouranId), restouranId);
    }

    @Override
    public Meal update(Meal meal, int restouranId) {
        checkNotFoundWithId(res_repository.get(restouranId), restouranId);
        return checkNotFoundWithId(repository.save(meal, restouranId), meal.getId());
    }
    @Override
    public Meal create(Meal meal, int restouranId) {
        checkNotFoundWithId(res_repository.get(restouranId), restouranId);
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restouranId);
    }



}
