package andrey.service;

import andrey.model.HistoryMeal;
import andrey.model.Meal;
import andrey.repository.MealRepository;
import andrey.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import andrey.repository.HistoryMealRepository;

import java.time.LocalDate;
import java.util.List;

import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class HistoryMealServiceImpl implements HistoryMealService {
public static final String NOT_YESTERDAY = "Вы можете обновить только за текщую дату!";
public static final String NOT_EQUALS_ID = "Не совпадают ID Meal и ID Meal in historyMeal";
    private final HistoryMealRepository repository;
    final private MealRepository mealRepository;

    @Autowired
    public HistoryMealServiceImpl(HistoryMealRepository repository, MealRepository mealRepository) {
        this.mealRepository = mealRepository;
        this.repository = repository;
    }
    @Override
    public HistoryMeal update(HistoryMeal historyMeal, Meal meal, long cost) {
        checkNotFoundWithId(meal, meal.getId());
        if (!historyMeal.getDate().isEqual(LocalDate.now())) {throw new NotFoundException(NOT_YESTERDAY);}
        if (!historyMeal.getMeal().getId().equals(meal.getId())) {throw new NotFoundException(NOT_EQUALS_ID);}
        HistoryMeal result = repository.save(historyMeal, meal, cost);
        return checkNotFoundWithId(result, result.getId());
    }

    @Override
    public HistoryMeal create(HistoryMeal historyMeal, Meal meal, long cost) {
        checkNotFoundWithId(meal, meal.getId());
        HistoryMeal result = repository.save(historyMeal,  meal, cost);
        return checkNotFoundWithId(result, result.getId());
    }

    @Override
    public HistoryMeal get(int meal_id) {
        return checkNotFoundWithId(repository.getMealId(meal_id), meal_id);
    }
    @Override
    public List<HistoryMeal> getAll() {
        return repository.getAll();
    }
}
