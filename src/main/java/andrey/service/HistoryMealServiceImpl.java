package andrey.service;

import andrey.model.HistoryMeal;
import andrey.model.List_of_admin;
import andrey.model.Meal;
import andrey.repository.MealRepository;
import andrey.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.repository.HistoryMealRepository;

import java.time.LocalDate;
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
    public HistoryMeal update(HistoryMeal historyMeal, int meal_Id, int cost, LocalDate date, int userId) {
        List_of_admin admin = threadLocalUtil.getList_of_admin();//текущий юзер есть в списке админов?
        if (!historyMeal.isNew()&& admin != null && date!=LocalDate.now() && mealRepository.get(meal_Id, admin.getRestouran().getId()) == null) {
            return null; // позволено удалять еду только с текущей датой: все люди ошибаются, админы тоже люди :))
        }
        Assert.notNull(historyMeal, "HistoryMeal(u) must not be null");
        return checkNotFoundWithId(repository.save(historyMeal,  meal_Id, cost, date,  userId), historyMeal.getId());}

    @Override //добавить обработку, не возвращать нулл!!!
    public HistoryMeal create(HistoryMeal historyMeal, int meal_Id, int cost, int userId) {
        List_of_admin admin = threadLocalUtil.getList_of_admin();
        if (!historyMeal.isNew()&& mealRepository.get(meal_Id, admin.getRestouran().getId()) == null) {
            return null;
        }
        Assert.notNull(historyMeal, "HistoryMeal(c) must not be null");
        return repository.save(historyMeal,  meal_Id, cost, LocalDate.now(),  userId);
    }
//добавить обработку, не возвращать нулл!!!
    @Override
    public void delete(int historyMeal_id, int user_id) {
        HistoryMeal check_date = repository.getId(historyMeal_id);
        if (check_date.getDate() != LocalDate.now()){
            return ;}

        List_of_admin admin = threadLocalUtil.getList_of_admin(); //проверка - принадлежит ли админ ресторану
        Meal chekMeal = mealRepository.get(check_date.getMeal().getId() , admin.getRestouran().getId()); // проверка - принадлежит ли еда ресторану

        if (admin.getRestouran().getId() == user_id && chekMeal != null){
        checkNotFoundWithId (repository.delete(historyMeal_id, user_id), historyMeal_id);
        }
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


}
