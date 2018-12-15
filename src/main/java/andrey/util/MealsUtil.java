package andrey.util;


import andrey.model.Meal;
import andrey.to.MealTo;
import andrey.to.MealToDb;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.Collections;

public class MealsUtil {

    private MealsUtil() {
    }
    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getName(), null, newMeal.getCost());
    }

    public static MealTo asTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getName(), meal.getRestouran().getId(), meal.getCost());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setName(mealTo.getName());
        meal.setId(mealTo.getId());
        meal.setCost(mealTo.getCost());
        return meal;
    }

}