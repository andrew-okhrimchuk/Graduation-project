package andrey.service;

import andrey.model.HistoryMeal;
import andrey.model.List_of_admin;
import andrey.to.MealTo;
import andrey.util.threadLocal.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import andrey.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.model.Meal;
import andrey.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static andrey.util.MealsUtil.asTo;
import static andrey.util.ValidationUtil.*;
import static andrey.util.exception.NotEnoughRightsException.ADMIN_LIST_EMPTY_EXEPTION;
import static andrey.util.exception.NotFoundException.NOT_FOUND_EXCEPTION;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;
    private ThreadLocalUtil threadLocalUtil;
    private HistoryMealService historyMealService;

    private static MealServiceImpl.Expression checkUserIsAdminOfList = (list, rest_id)-> list.getRestouran().getId()==rest_id;


    @Autowired
    public MealServiceImpl(MealRepository repository, ThreadLocalUtil threadLocalUtil, HistoryMealService historyMealService) {
        this.repository = repository;
        this.threadLocalUtil = threadLocalUtil;
        this.historyMealService = historyMealService;
    }


    @Override
    public Meal get(int id, LocalDate date) throws NotFoundException{
        if (date == null) {return checkNotFoundWithId(repository.getWithLastCost(id), id); }
        else return checkNotFoundWithId(repository.getWithDate(id, date), id);
    }


    @Override
    public List<Meal> getAll(int restouran_id) {
        return repository.getAll(restouran_id);
    }

    @Override
    public void delete(int id) {
        Meal delete = repository.getWithLastCost(id);
        checkNotFoundWithId(delete, id);
        checkMealIsOwnOfRestouran(asTo(delete), checkUserIsAdminOfList);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public MealTo update(MealTo meal) {
        Assert.notNull(meal, "meal must not be null");
        // проверка на админность
        checkMealIsOwnOfRestouran(meal, checkUserIsAdminOfList);
        // сохранение и получение обновленой еды
        Meal mTo = repository.save(meal);
        checkNotFoundWithId(mTo, mTo.getId());
        // запрос на получение старой стории еды
        HistoryMeal oldHistoryMeal = historyMealService.get(mTo.getId());
        // отправка объекта на обновление в репозиторий истории еды
        HistoryMeal newHistoryMeal = historyMealService.update(oldHistoryMeal, mTo, meal.getCost());
        // возврат готового резутьтата в виде ТО - MealTO
        mTo.setCost(newHistoryMeal.getCost());
        return  asTo(mTo);
    }
    @Override
    public MealTo create(MealTo meal) {
        Assert.notNull(meal, "meal must not be null");
        // проверка на админность
        checkMealIsOwnOfRestouran(meal, checkUserIsAdminOfList);
        // сохранение и получение новой еды
        Meal mTo = repository.save(meal);
        checkNotFoundWithId(mTo, mTo.getId());
        // создание объекта истории новой еды
        HistoryMeal oldHistoryMeal = new HistoryMeal();
        // отправка объекта на обновление в репозиторий истории еды
        historyMealService.create(oldHistoryMeal, mTo, meal.getCost());
        // возврат готового резутьтата в виде ТО - MealTO
        return  asTo(mTo);
    }

    public void checkMealIsOwnOfRestouran(MealTo meal, MealServiceImpl.Expression func) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        checkNotFound(list_of_admin, ADMIN_LIST_EMPTY_EXEPTION);
        checkNotFound(meal, NOT_FOUND_EXCEPTION);

        boolean isOk = false;
        int restoura_id = meal.getRestouran_id();
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
