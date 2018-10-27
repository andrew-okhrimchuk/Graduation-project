package andrey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

// where e.salary = coalesce(e.salary, :sal)
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL_2, query = "SELECT u FROM User u WHERE u.email=:email"),
// рабочая версия , только помкенять д.время
      //  @NamedQuery(name = User.BY_EMAIL_3, query = "SELECT u, coalesce(b.dateTime, :date_0), coalesce(b.isSecondVotin, false) FROM User u LEFT JOIN HistoryVoting b ON u.id = b.user.id WHERE  u.email=:email AND b.dateTime is null "),
      @NamedQuery(name = User.BY_EMAIL_3, query = "SELECT NEW andrey.to.UserToDb(u, b.dateTime, coalesce(b.isSecondVotin, false)) FROM User u LEFT JOIN HistoryVoting b ON u.id = b.user.id WHERE u.email=:email ORDER BY b.dateTime "),


        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users")
@SecondaryTable(name = "HISTORY_VOTING", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID" ))
@Getter @Setter
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL_2 = "User.getByEmail";
    public static final String BY_EMAIL_3 = "User.getByEmai";
    public static final String ALL_SORTED = "User.getAllSorted";


    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
 //   @SafeHtml(groups = {View.Web.class})  // https://stackoverflow.com/questions/17480809
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
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
    @Column(table ="HISTORY_VOTING", nullable = true)
    private boolean issecondvoitin;

    @Transient
    @JsonIgnore
    @Column(table ="HISTORY_VOTING", nullable = true)
    private LocalDate dateVoitin;


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
    this.issecondvoitin = user.issecondvoitin;
    this.dateVoitin = user.dateVoitin;
  }

    @Transient
    @JsonIgnore
    public boolean isVoting() {
        return issecondvoitin;
    }

    @Transient
    @JsonIgnore
    public LocalDate getDateVoitin() {
        return dateVoitin;
    }

}