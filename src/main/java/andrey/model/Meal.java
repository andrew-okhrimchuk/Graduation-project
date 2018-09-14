package andrey.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id AND u.restouran.id=:restouranId"),
        @NamedQuery(name = Meal.DELETE_All, query = "DELETE FROM Meal"),
        @NamedQuery(name = Meal.BY_FIND, query = "SELECT u FROM Meal u WHERE u.id=:id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u WHERE u.restouran.id=:restouranId ORDER BY u.name DESC ")
})

@Entity
@Table(name = "meals")
@SecondaryTable(name = "HISTORY_MEAL", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
@Getter @Setter
public class Meal extends AbstractNamedEntity {

    public static final String DELETE = "Meal.delete";
    public static final String DELETE_All = "Meal.deleteAll";
    public static final String BY_FIND = "Meal.getById";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restouran restouran;

    @Transient
    @Column(table ="HISTORY_MEAL", nullable = false)
    private int cost;

    public Meal() {
    }

    public Meal(@NotNull Integer id,
                @NotNull String name,
                @NotNull Restouran restouran) {
        super(id, name);
        this.restouran = restouran;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "restouran=" + restouran +
                ", id=" + id +
                '}';
    }
}
