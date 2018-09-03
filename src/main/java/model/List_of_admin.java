package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "list_of_admin") //id, дата, restoran, User)
public class List_of_admin {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_admim", nullable = false)
    private List<User> user;

    public List_of_admin(@NotNull Integer id, Restouran restouran, @NotNull List<User> user) {
        this.id = id;
        this.restouran = restouran;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restouran getRestouran() {
        return restouran;
    }

    public void setRestouran(Restouran restouran) {
        this.restouran = restouran;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
