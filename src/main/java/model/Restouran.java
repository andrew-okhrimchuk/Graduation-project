package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Restouran.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.ALL_SORTED, query = "SELECT u FROM Meal u WHERE u.user.id=:userId ORDER BY u.dateTime DESC "),
        @NamedQuery(name = Restouran.getBetween, query = "SELECT u FROM Meal u WHERE u.user.id=:userId AND u.dateTime >=:startDate AND u.dateTime <=:endDate ORDER BY u.dateTime DESC ")
})
@Entity
@Table(name = "Restouran")
public class Restouran {

    public static final String DELETE1 = "Meal.delete";
    public static final String BY_FIND1 = "Meal.getById";
    public static final String getBetween1 = "Meal.getBetween";
    public static final String ALL_SORTED1 = "Meal.getAllSorted";

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

    public Restouran() {
    }

    public Restouran(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restouran{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
