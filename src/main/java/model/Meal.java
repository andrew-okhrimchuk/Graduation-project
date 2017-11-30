package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Meal.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u WHERE u.user.id=:userId ORDER BY u.dateTime DESC "),
        @NamedQuery(name = Meal.getBetween, query = "SELECT u FROM Meal u WHERE u.user.id=:userId AND u.dateTime >=:startDate AND u.dateTime <=:endDate ORDER BY u.dateTime DESC ")
})
@Entity
@Table(name = "meals")
public class Meal  {

    public static final String DELETE = "Meal.delete";
    public static final String BY_FIND = "Meal.getById";
    public static final String getBetween = "Meal.getBetween";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "cost", nullable = false)
    private long cost;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;


    public Meal() {
    }

    public Meal(int id, String name, long cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
