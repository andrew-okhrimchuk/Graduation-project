package andrey.model;

import com.fasterxml.jackson.databind.annotation.*;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.DELETE_All, query = "DELETE FROM Meal"),
        @NamedQuery(name = Meal.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.BY_FINDDATE, query = "SELECT NEW andrey.to.MealToDb(u, coalesce(h.cost, 0)) FROM Meal u LEFT JOIN HistoryMeal h ON u.id = h.meal.id WHERE u.id=:id AND h.date=:date"),
        @NamedQuery(name = Meal.BY_FINDLastDATE, query = "SELECT NEW andrey.to.MealToDb(u, coalesce(h.cost, 0)) FROM Meal u LEFT JOIN HistoryMeal h ON u.id = h.meal.id WHERE u.id=:id ORDER BY h.date DESC"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT NEW andrey.to.MealToDb(u, coalesce(h.cost, 0)) FROM Meal u LEFT JOIN HistoryMeal h ON u.id = h.meal.id WHERE u.restouran.id=:restouranId ORDER BY h.date DESC")
})
@Entity
@Table(name = "meals")
@DynamicUpdate
@SecondaryTable(name = "HISTORY_MEAL", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
@Getter @Setter @ToString
public class Meal extends AbstractNamedEntity {

    public static final String DELETE = "Meal.delete";
    public static final String DELETE_All = "Meal.deleteAll";
    public static final String BY_FIND = "Meal.getById";
    public static final String BY_FINDDATE = "Meal.getByIdDate";
    public static final String BY_FINDLastDATE = "Meal.getByIdLastDate";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound( action = NotFoundAction.IGNORE)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @Transient
  //  @JsonSerialize
   // @JsonDeserialize
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(updatable = false,insertable = false ,table = "HISTORY_MEAL", name = "COST", nullable = false)
    private long cost;

    public Meal() {
    }

    public Meal(@NotNull Integer id,
                @NotNull String name,
                @NotNull Restouran restouran) {
        super(id, name);
        this.restouran = restouran;
    }

    public Meal(@NotNull Integer id,
                @NotNull String name,
                @NotNull Restouran restouran, long cost) {
        super(id, name);
        this.restouran = restouran;
        this.cost = cost;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
