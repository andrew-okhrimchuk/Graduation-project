package andrey.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_MEAL_ID, query = "SELECT u FROM HistoryMeal u WHERE u.meal.id=:meal_id"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_MEAL_IDDate, query = "SELECT u FROM HistoryMeal u WHERE u.meal.id=:id AND u.date=:date"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_DATE_Between, query = "SELECT u FROM HistoryMeal u WHERE u.date>=:start AND u.date<=:end ORDER BY u.date DESC"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_COST, query = "SELECT u FROM HistoryMeal u WHERE u.cost=:cost"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_MEAL_All, query = "SELECT u FROM HistoryMeal u ORDER BY u.date DESC"),
        @NamedQuery(name = HistoryMeal.DELETE, query = "DELETE FROM HistoryMeal u WHERE u.id=:id")
        })

@Entity
@Table(name = "history_meal")
@Getter @Setter @ToString
public class HistoryMeal extends AbstractBaseEntity {
    public static final String GET_HISTORY_BY_MEAL_ID = "HistoryMeal.getByDate";
    public static final String GET_HISTORY_BY_MEAL_IDDate = "HistoryMeal.getByIdDate";
    public static final String GET_HISTORY_BY_DATE_Between = "HistoryMeal.getByDateBetween";
    public static final String GET_HISTORY_BY_RESTOURAN_ID = "HistoryMeal.getByRestouranId";
    public static final String GET_HISTORY_BY_COST = "HistoryMeal.getByCost";
    public static final String GET_HISTORY_MEAL_All = "HistoryMeal.getAll";
    public static final String DELETE = "HistoryMeal.delete";


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "cost", nullable = false)
    private long cost;

    public HistoryMeal(Integer id, @NotNull Meal meal, @NotNull LocalDate date, @NotNull long cost) {
        super(id);
        this.meal = meal;
        this.date = date;
        this.cost = cost;
    }

    public HistoryMeal() {
    }
    public boolean isNew() {
        return this.id == null;
    }

}
