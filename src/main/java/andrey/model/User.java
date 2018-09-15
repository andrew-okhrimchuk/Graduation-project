package andrey.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.BY_EMAIL_2, query = "SELECT u FROM User u LEFT JOIN FETCH HistoryVoting b LEFT JOIN FETCH HistoryVoting c WHERE b.id=u.id AND b.dateTime=:dateTime AND u.email=:email"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users")
@SecondaryTable(name = "HISTORY_VOTING", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
@Getter @Setter
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String BY_EMAIL_2 = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    /*@Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;*/

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
 //   @SafeHtml(groups = {View.Web.class})  // https://stackoverflow.com/questions/17480809
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Transient
    @Column(table ="HISTORY_VOTING", nullable = true)
    private boolean issecondvoitin;

    @Transient
    @Column(table ="HISTORY_VOTING", nullable = true)
    private LocalDateTime dateVoitin;



    public boolean isNew() {
        return this.id == null;
    }

    public User() {}


    public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Transient
    public boolean isVoting() {
        return issecondvoitin;
    }

    @Transient
    public LocalDateTime getDateVoitin() {
        return dateVoitin;
    }

}