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
    private final Integer id;

    @NotNull
    @Column(name = "dateTime", nullable = false)
    private final LocalDateTime dateTime;

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