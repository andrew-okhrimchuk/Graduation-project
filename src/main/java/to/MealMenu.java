package to;

import lombok.Getter;
import lombok.Setter;
import model.Meal;
import model.Restouran;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;
@Getter @Setter
public class MealMenu {

    private String restouranName;
    private String meal;
    private long cost;

    public MealMenu(String restouranName, String meal, long cost) {
        this.restouranName = restouranName;
        this.meal = meal;
        this.cost = cost;
    }

    public MealMenu() {
    }


}