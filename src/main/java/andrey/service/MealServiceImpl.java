package andrey.service;

import andrey.model.List_of_admin;
import andrey.repository.RestouranRepository;
import andrey.util.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
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
    private ThreadLocalUtil threadLocalUtil;

    private static MealServiceImpl.Expression checkUserIsAdminOfList = (list, rest_id)-> list.getRestouran().getId()==rest_id;


    @Autowired
    public MealServiceImpl(MealRepository repository, RestouranRepository res_repository, ThreadLocalUtil threadLocalUtil) {
        this.repository = repository;
        this.res_repository = res_repository;
        this.threadLocalUtil = threadLocalUtil;
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
    public void delete(int id) {
        checkMealIsOwnOfRestouran(repository.get(id), checkUserIsAdminOfList);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal update(Meal meal) {
        checkMealIsOwnOfRestouran(meal, checkUserIsAdminOfList);
        return checkNotFoundWithId(repository.save(meal), meal.getId());
    }
    @Override
    public Meal create(Meal meal) {
        checkMealIsOwnOfRestouran(meal, checkUserIsAdminOfList);
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }

    public  void checkMealIsOwnOfRestouran(Meal meal, MealServiceImpl.Expression func) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        boolean isOk = false;
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        if (list_of_admin == null) {
            throw new NotEnoughRightsException("Users list is empty. User is not admin this restouran!");
        }
        if (meal.getRestouran() == null) {
            throw new NotEnoughRightsException("Meal id = " + meal.getId() + ". Meal is not own Of restouran = null!");
        }

        else {
            int restoura_id = meal.getRestouran().getId();
            for (List_of_admin admin : list_of_admin) {
                if (func.isEqual(admin, restoura_id)) {
                    isOk = true;
                }
            }
        }

        if (!isOk) {
            throw new NotEnoughRightsException("Meal = " + meal);
        }
    }

    interface Expression{
        boolean isEqual(List_of_admin list, int restoura_id);
    }
}
