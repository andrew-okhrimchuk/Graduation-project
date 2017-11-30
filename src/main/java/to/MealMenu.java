package to;

import model.Meal;
import model.Restouran;
import java.time.LocalDateTime;

public class MealMenu {

    private  Integer id;
    private LocalDateTime dateTime;
    private Restouran restouran;
    private Meal meal;

    public MealMenu(Integer id, LocalDateTime dateTime, Restouran restouran, Meal meal) {
        this.id = id;
        this.dateTime = dateTime;
        this.restouran = restouran;
        this.meal = meal;
    }

    public MealMenu() {
    }

    @Override
    public String toString() {
        return "MealMenu{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", restouran=" + restouran +
                ", meal=" + meal +
                '}';
    }
}