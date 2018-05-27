package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime=:dateTime"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime=:dateTime and u.user=:userId"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_RESTOURAN_ID, query = "SELECT u FROM HistoryVoting u WHERE u.restouran=:restouran"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.user=:userId"),
        @NamedQuery(name = HistoryVoting.GET_All, query = "SELECT u FROM HistoryVoting u")
})

@Entity
@Table(name = "history_voting") //id, дата, restoran, User)
public class HistoryVoting {

    public static final String GET_VOTING_BY_DATE = "HistoryVoting.getByDate";
    public static final String GET_VOTING_BY_DATE_BY_USER_ID = "HistoryVoting.getByDateByUserId";
    public static final String GET_VOTING_BY_RESTOURAN_ID = "HistoryVoting.getByRestouranId";
    public static final String GET_VOTING_BY_USER_ID = "HistoryVoting.getByUserId";
    public static final String GET_All = "HistoryVoting.getAll";

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private  Integer id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public HistoryVoting(@NotNull Integer id,
                         @NotNull LocalDateTime dateTime,
                         @NotNull Restouran restouran,
                         @NotNull User user) {
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
    public boolean isNew() {
        return this.id == null;
    }

}
