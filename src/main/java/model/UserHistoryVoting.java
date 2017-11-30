package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_history_voting")
public class UserHistoryVoting {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @NotNull
    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;

    public UserHistoryVoting(@NotNull int id, @NotNull User user, @NotNull LocalDateTime dateTime, @NotNull Restouran restouran) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.restouran = restouran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "UserHistoryVoting{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", restouran=" + restouran +
                '}';
    }
}
