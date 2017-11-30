package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_voting") //id, дата, restoran, User)
public class HistoryVoting {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id", nullable = false)
    private User user;

    public HistoryVoting(@NotNull Integer id, @NotNull LocalDateTime dateTime, Restouran restouran, @NotNull User user) {
        this.id = id;
        this.dateTime = dateTime;
        this.restouran = restouran;
        this.user = user;
    }

    public HistoryVoting() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restouran getRestouran() {
        return restouran;
    }

    public void setRestouran(Restouran restouran) {
        this.restouran = restouran;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HistoryVoting{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", restouran=" + restouran +
                ", user=" + user +
                '}';
    }
}
