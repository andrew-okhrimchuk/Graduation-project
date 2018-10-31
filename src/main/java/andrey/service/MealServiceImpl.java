package andrey.service;

import andrey.model.List_of_admin;
import andrey.repository.RestouranRepository;
import andrey.util.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import andrey.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.model.Meal;
import andrey.repository.MealRepository;

import java.util.List;
import static andrey.util.ValidationUtil.*;
import static andrey.util.exception.NotEnoughRightsException.ADMIN_LIST_EMPTY_EXEPTION;
import static andrey.util.exception.NotFoundException.NOT_FOUND_EXCEPTION;

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
        Assert.notNull(meal, "meal must not be null");
        checkMealIsOwnOfRestouran(meal, checkUserIsAdminOfList);
        Meal result = repository.save(meal);
        return result;
    }

    public void checkMealIsOwnOfRestouran(Meal meal, MealServiceImpl.Expression func) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        checkNotFound(list_of_admin, ADMIN_LIST_EMPTY_EXEPTION);
        checkNotFound(meal, NOT_FOUND_EXCEPTION);
        checkNotFound(meal.getRestouran(), "Meal id = " + meal.getId() + ". Meal is not own Of restouran = null!");

        boolean isOk = false;
        int restoura_id = meal.getRestouran().getId();
            for (List_of_admin admin : list_of_admin) {
                if (func.isEqual(admin, restoura_id)) {
                    isOk = true;
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
