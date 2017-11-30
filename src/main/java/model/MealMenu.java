package model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "menu")
public class MealMenu {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    public MealMenu(Integer id, Restouran restouran, Meal meal) {
        this.id = id;
        this.restouran = restouran;
        this.meal = meal;
    }

    public MealMenu() {
    }

    @Override
    public String toString() {
        return "MealMenu{" +
                "id=" + id +
                ", restouran=" + restouran +
                ", meal=" + meal +
                '}';
    }
}