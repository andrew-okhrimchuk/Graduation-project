package andrey.model;

import andrey.to.MealMenu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restouran.DELETE, query = "DELETE FROM Restouran r WHERE r.id=:id"),
        @NamedQuery(name = Restouran.BY_FIND, query = "SELECT u FROM Restouran u WHERE u.id=:id"),
        @NamedQuery(name = Restouran.ALL_SORTED, query = "SELECT u FROM Restouran u ORDER BY u.name DESC "),
        @NamedQuery(name = Restouran.MANU, query = "SELECT NEW andrey.to.MealMenu(r.name, m.name, h.cost) FROM Restouran r JOIN Meal m ON r.id = m.restouran.id JOIN HistoryMeal h ON m.id = h.meal.id WHERE h.date=:date")
})
@Entity
@Table(name = "restouran")
@SecondaryTables(
        {@SecondaryTable(name = "MEALS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" )),
         @SecondaryTable(name = "LIST_OF_ADMIN", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
        })
@Getter @Setter
public class Restouran extends AbstractNamedEntity {

    public static final String DELETE = "Restouran.delete";
    public static final String BY_FIND = "Restouran.getById";
    public static final String ALL_SORTED = "Restouran.getAllSorted";
    public static final String MANU = "Restouran.getAllToday";

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull(groups = View.Persist.class)
    private User user;*/ //

    @Transient
    @Column(table ="MEALS", nullable = true)
    private List<Meal> meals;

    @Transient
    @Column(table ="LIST_OF_ADMIN", nullable = true)
    private List<List_of_admin> list_of_admin;

    public Restouran() {}

    public Restouran(@NotNull Integer id,
                     @NotNull String name
                     ) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restouran{" +
                "id=" + id +
                '}';
    }



    public boolean isNew() {
        return this.id == null;
    }
}
