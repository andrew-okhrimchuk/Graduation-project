package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.DELETE_All, query = "DELETE FROM Meal"),
        @NamedQuery(name = Meal.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id")
       // @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u WHERE u.dateTime >=:startDate AND u.dateTime <=:endDate ORDER BY u.dateTime DESC "),
      //  @NamedQuery(name = Meal.ALL_SORTED_RESTOURAN, query = "SELECT u FROM Meal u WHERE u.dateTime >=:startDate AND u.dateTime <=:endDate AND u.restouran.id=:restouranId ORDER BY u.dateTime DESC "),
})

@Entity
@Table(name = "meals")
public class Meal  {

    public static final String DELETE = "Meal.delete";
    public static final String DELETE_All = "Meal.deleteAll";
    public static final String BY_FIND = "Meal.getById";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String ALL_SORTED_RESTOURAN = "Meal.getAllSortedRestouran";

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "cost", nullable = false)
    private long cost;


    public Meal() {
    }


    public Meal(@NotNull Integer id, @NotNull String name, @NotNull long cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    public Meal(String name, long cost, Restouran restouran) {
        this(null, name, cost);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
