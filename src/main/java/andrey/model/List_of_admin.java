package andrey.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = List_of_admin.DELETE, query = "DELETE FROM List_of_admin u WHERE u.id=:id"),
        @NamedQuery(name = List_of_admin.BY_ID, query = "SELECT u FROM List_of_admin u  WHERE u.id=:id"),
        @NamedQuery(name = List_of_admin.BY_RESTOURAN_ID, query = "SELECT u FROM List_of_admin u WHERE u.restouran.id=:restouran_id"),
        //@NamedQuery(name = List_of_admin.BY_ADMIN_ID, query = "SELECT OBJECT(u) FROM List_of_admin u WHERE u.BY_USER_ID_ADMIN=:user_id_admin"),
        @NamedQuery(name = List_of_admin.BY_ADMIN_ID, query = "SELECT u FROM List_of_admin u WHERE u.user.id=:admin_id"),
        @NamedQuery(name = List_of_admin.ALL_SORTED, query = "SELECT r FROM List_of_admin r ORDER BY r.restouran.name"),
})
@Entity
@Table(name = "list_of_admin") //id, дата, restoran, User)
@Getter @Setter
public class List_of_admin {
    public static final String DELETE = "LIST_OF_ADMIN.delete";
    public static final String BY_ID = "LIST_OF_ADMIN.getById";
    public static final String BY_RESTOURAN_ID = "LIST_OF_ADMIN.getByRestouranId";
    public static final String BY_ADMIN_ID = "LIST_OF_ADMIN.getByUSER_ID_ADMIN";
    public static final String ALL_SORTED = "LIST_OF_ADMIN.getAllSorted";

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_admin", nullable = false)
    private User user;

    public List_of_admin(@NotNull Integer id,@NotNull Restouran restouran, @NotNull User user) {
        this.id = id;
        this.restouran = restouran;
        this.user = user;
    }

    public List_of_admin() {
    }
    public boolean isNew() {
        return this.id == null;
    }

}
