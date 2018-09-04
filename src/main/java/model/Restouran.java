package model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Restouran.DELETE, query = "DELETE FROM Restouran u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.BY_FIND, query = "SELECT u FROM Restouran u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.ALL_SORTED, query = "SELECT u FROM Restouran u WHERE u.user.id=:userId ORDER BY u.name DESC ")
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
