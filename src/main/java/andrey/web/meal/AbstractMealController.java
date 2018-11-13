package andrey.web.meal;

import andrey.AuthorizedUser;
import andrey.model.Meal;
import andrey.service.MealService;
import andrey.to.MealTo;
import andrey.util.MealsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static andrey.util.ValidationUtil.assureIdConsistent;
import static andrey.util.ValidationUtil.checkNew;


public abstract class AbstractMealController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;


    public Meal get(int id, LocalDate date) {
        log.info("get meal {} and date {} ", id, date);
        return service.get(id, date);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id);
    }

    public List<Meal> getAll(int restouran_id) {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return service.getAll(restouran_id);
    }

    public MealTo create(MealTo meal) {
        int userId = AuthorizedUser.id();
        checkNew(meal);
        log.info("create {} for user {}", meal, userId);
        return service.create(meal);
    }

    public void update(MealTo meal, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(meal, id);
        log.info("update {} for user {}", meal, userId);
        service.update(meal);
    }
}