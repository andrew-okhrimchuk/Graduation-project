package andrey.to;

import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "MealMenu{" +
                "restouranName='" + restouranName + '\'' +
                ", meal='" + meal + '\'' +
                ", cost=" + cost +
                '}';
    }

}