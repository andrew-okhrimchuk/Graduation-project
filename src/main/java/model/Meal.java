package model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.DELETE_All, query = "DELETE FROM Meal"),
        @NamedQuery(name = Meal.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u ORDER BY u.name DESC ")
})

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {

    public static final String DELETE = "Meal.delete";
    public static final String DELETE_All = "Meal.deleteAll";
    public static final String BY_FIND = "Meal.getById";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull(groups = View.Persist.class)
    private User user;

    public Meal() {
    }

    public Meal(@NotNull Integer id,
                @NotNull String name,
                @NotNull User user) {
        super(id, name);
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "user=" + user +
                ", id=" + id +
                '}';
    }
}
