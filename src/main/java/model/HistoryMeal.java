package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate; //HISTORY

@NamedQueries({
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_MEAL_ID, query = "SELECT u FROM HistoryMeal u WHERE u.meal=:meal"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_DATE, query = "SELECT u FROM HistoryMeal u WHERE u.date=:date"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_RESTOURAN_ID, query = "SELECT u FROM HistoryMeal u WHERE u.restouran=:restouran"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_BY_COST, query = "SELECT u FROM HistoryMeal u WHERE u.cost=:cost"),
        @NamedQuery(name = HistoryMeal.GET_HISTORY_MEAL_All, query = "SELECT u FROM HistoryMeal u ORDER BY u.date DESC")
        })

@Entity
@Table(name = "history_meal")  // id, meal_id, data, restoran_id, cost
public class HistoryMeal {
    public static final String GET_HISTORY_BY_MEAL_ID = "HistoryMeal.getByDate";
    public static final String GET_HISTORY_BY_DATE = "HistoryMeal.getByDate";
    public static final String GET_HISTORY_BY_RESTOURAN_ID = "HistoryMeal.getByRestouranId";
    public static final String GET_HISTORY_BY_COST = "HistoryMeal.getByDate";
    public static final String GET_HISTORY_MEAL_All = "HistoryMeal.getAll";


    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @NotNull
    @Column(name = "cost", nullable = false)
    private long cost;

    public HistoryMeal(@NotNull Integer id,
                       @NotNull Meal meal,
                       @NotNull LocalDate date,
                       @NotNull Restouran restouran,
                       @NotNull long cost) {
        this.id = id;
        this.date = date;
        this.restouran = restouran;
        this.meal = meal;
        this.cost = cost;
    }

    public HistoryMeal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restouran getRestouran() {
        return restouran;
    }

    public void setRestouran(Restouran restouran) {
        this.restouran = restouran;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "HistoryMeal{" +
                "id=" + id +
                ", meal=" + meal +
                ", date=" + date +
                ", restouran=" + restouran +
                ", cost=" + cost +
                '}';
    }

    public boolean isNew() {
        return this.id == null;
    }

}
