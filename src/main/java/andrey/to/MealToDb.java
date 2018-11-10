package andrey.to;

import andrey.model.Meal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MealToDb {

    private Meal meal;
    private long cost;

    public MealToDb(Meal meal, long cost) {
        this.meal = meal;
        this.cost = cost;
    }
    public Meal init(){
        if (cost >= 0 ){
            meal.setCost(cost);
        }
        return meal;


    }
    public MealToDb() {
    }


}