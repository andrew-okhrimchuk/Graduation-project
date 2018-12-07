package andrey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@NamedQueries({
      @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
      @NamedQuery(name = User.BY_EMAIL_2, query = "SELECT u FROM User u WHERE u.email=:email"),
      //@NamedQuery(name = User.BY_EMAIL_3, query = "SELECT NEW andrey.to.UserToDb(u, b.dateTime, coalesce(b.isSecondVotin, false)) FROM User u LEFT JOIN HistoryVoting b ON u.id = b.user.id WHERE u.email=:email ORDER BY b.dateTime "),
      @NamedQuery(name = User.BY_EMAIL_3, query = "SELECT NEW andrey.to.UserToDb(u, b) FROM User u LEFT JOIN HistoryVoting b ON u.id = b.user WHERE u.email=:email ORDER BY b.dateTime "),
      @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "USERS_UNIQUE_EMAIL_IDX")})
@SecondaryTable(name = "HISTORY_VOTING", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
@Getter @Setter @ToString
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL_2 = "User.getByEmail";
    public static final String BY_EMAIL_3 = "User.getByEmai";
    public static final String ALL_SORTED = "User.getAllSorted";


    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @Transient
    @JsonIgnore
    @Column(table ="HISTORY_VOTING")
    private HistoryVoting historyVoting;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    public User() {}



  public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password) {
    super(id, name);
    this.email = email;
    this.password = password;
  }

  public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password, Set<Role> roles) {
    super(id, name);
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public User(@NotBlank @NotNull @NotEmpty User user) {
    super(user.id, user.name);
    this.email = user.email;
    this.password = user.password;
    this.roles = user.roles;
    this.historyVoting = user.historyVoting;
  }


}