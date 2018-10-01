package andrey.service;

import andrey.model.HistoryMeal;
import andrey.model.List_of_admin;
import andrey.model.Meal;
import andrey.model.Restouran;
import andrey.repository.MealRepository;
import andrey.util.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.repository.HistoryMealRepository;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class HistoryMealServiceImpl implements HistoryMealService {

    private final HistoryMealRepository repository;
    final private MealRepository mealRepository;
    final private ThreadLocalUtil threadLocalUtil;

    @Autowired
    public HistoryMealServiceImpl(HistoryMealRepository repository, MealRepository mealRepository, ThreadLocalUtil threadLocalUtil) {
        this.mealRepository = mealRepository;
        this.threadLocalUtil = threadLocalUtil;
        this.repository = repository;
    }

    @Override //добавить обработку, не возвращать нулл!!!
    public HistoryMeal update(HistoryMeal historyMeal, int meal_Id, int cost, int userId) {
        Assert.notNull(historyMeal, "HistoryMeal(u) must not be null");
        checkUserIsAdmin(userId);
      //  isMealBelongRestouran (historyMeal.getId()); // позволено удалять еду только с текущей датой: все люди ошибаются, админы тоже люди :))
        return checkNotFoundWithId(repository.save(historyMeal,  meal_Id, cost, userId), historyMeal.getId());}

    @Override //добавить обработку, не возвращать нулл!!!
    public HistoryMeal create(HistoryMeal historyMeal, int meal_Id, int cost, int userId) {
        Assert.notNull(historyMeal, "HistoryMeal(c) must not be null");
        if (!historyMeal.isNew()) {return null;}
        checkUserIsAdmin(userId);
       // isMealBelongRestouran (historyMeal.getId());
        return repository.save(historyMeal,  meal_Id, cost,  userId);
    }
//добавить обработку, не возвращать нулл!!!
    @Override
    public void delete(int historyMeal_id, int user_id) {
      //  isMealBelongRestouran (historyMeal_id); /// проверка - принадлежит ли еда ресторану
        checkUserIsAdmin (user_id); //текущий юзер есть в списке админов?
        checkNotFoundWithId (repository.delete(historyMeal_id, user_id), historyMeal_id);
    }

    @Override
    public List<HistoryMeal> getByMealId(int id){
        Assert.notNull(id, "meal_id must not be null");
        return repository.getByMealId(id);
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


    public  void checkUserIsAdmin(int userId) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        boolean isOk = false;
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        if (list_of_admin == null) {
            throw  new NotEnoughRightsException("User id = " + userId + " not enough rights");
        }
        else {
            for (List_of_admin admin : list_of_admin) {
                if (admin.getUser().getId() == userId) {
                    isOk = true;
                }
            }
        }

        if (!isOk) {throw new NotEnoughRightsException("User id = " + userId + " not enough rights");
        }
    }
    public  void  isMealBelongRestouran (int historyMeal_id){
        List<List_of_admin> admin = threadLocalUtil.getList_of_admin();
        List<Integer> r_id = new ArrayList<>();

        for (List_of_admin list : admin){r_id.add(list.getRestouran().getId());}
        List<HistoryMeal> xx = repository.isMealBelongRestouran (historyMeal_id, r_id);
        Assert.notNull(xx, "Meal not belong this restouran");
    }

}
