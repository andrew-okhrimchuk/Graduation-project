package to;


import model.Meal;
import model.Restouran;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;


public class MealMenu {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
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