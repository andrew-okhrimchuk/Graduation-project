package andrey.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate; //HISTORY

@NamedQueries({
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_ID, query = "SELECT u FROM HistoryMeal u WHERE u.id=:id"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_MEAL_ID, query = "SELECT u FROM HistoryMeal u WHERE u.meal=:meal_id"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_DATE_Between, query = "SELECT u FROM HistoryMeal u WHERE u.date>=:start AND u.date<=:end ORDER BY u.date DESC"),
       // @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_RESTOURAN_ID, query = "SELECT u FROM HistoryMeal u WHERE u.restouran=:restouran"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_COST, query = "SELECT u FROM HistoryMeal u WHERE u.cost=:cost"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_MEAL_All, query = "SELECT u FROM HistoryMeal u ORDER BY u.date DESC"),
        @NamedQuery(name = HistoryMeal.DELETE, query = "DELETE FROM HistoryMeal u WHERE u.id=:id")
        })

@Entity
@Table(name = "history_meal")  // id, meal_id, data, restoran_id, cost
@Getter @Setter
public class HistoryMeal {
    public static final String GET_HISTORY_BY_ID = "HistoryMeal.getById";
    public static final String GET_HISTORY_BY_MEAL_ID = "HistoryMeal.getByDate";
    public static final String GET_HISTORY_BY_DATE_Between = "HistoryMeal.getByDateBetween";
    public static final String GET_HISTORY_BY_RESTOURAN_ID = "HistoryMeal.getByRestouranId";
    public static final String GET_HISTORY_BY_COST = "HistoryMeal.getByCost";
    public static final String GET_HISTORY_MEAL_All = "HistoryMeal.getAll";
    public static final String DELETE = "HistoryMeal.delete";


    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

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

    public HistoryMeal(@NotNull Integer id,
                       @NotNull Meal meal,
                       @NotNull LocalDate date,
                       @NotNull long cost) {
        this.id = id;
        this.date = date;
        this.meal = meal;
        this.cost = cost;
    }

    public HistoryMeal() {
    }



    @Override
    public String toString() {
        return "HistoryMeal{" +
                "id=" + id +
                ", meal=" + meal +
                ", date=" + date +
                ", restouran=" +
                ", cost=" + cost +
                '}';
    }

    public boolean isNew() {
        return this.id == null;
    }

}
