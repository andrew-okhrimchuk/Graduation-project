package model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Restouran.DELETE, query = "DELETE FROM Restouran u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.BY_FIND, query = "SELECT u FROM Restouran u WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = Restouran.ALL_SORTED, query = "SELECT u FROM Restouran u WHERE u.user.id=:userId ORDER BY u.name DESC ")
})
@Entity
@Table(name = "restouran")
@Getter @Setter
public class Restouran extends AbstractNamedEntity {

    public static final String DELETE = "Restouran.delete";
    public static final String BY_FIND = "Restouran.getById";
    public static final String ALL_SORTED = "Restouran.getAllSorted";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull(groups = View.Persist.class)
    private User user;

    public Restouran() {}

    public Restouran(@NotNull Integer id,
                     @NotNull String name,
                     @NotNull User user) {
        super(id, name);
        this.user = user;
    }

    @Override
    public String toString() {
        return "Restouran{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }



    public boolean isNew() {
        return this.id == null;
    }
}
