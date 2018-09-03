package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_ID, query = "SELECT u FROM HistoryVoting u WHERE u.id=:id"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE_Between, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime>=:start AND u.dateTime<=:end ORDER BY u.dateTime DESC"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime=:dateTime and u.user=:userId"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_RESTOURAN_ID, query = "SELECT u FROM HistoryVoting u WHERE u.restouran=:restouran"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.user=:userId"),
        @NamedQuery(name = HistoryVoting.GET_All, query = "SELECT u FROM HistoryVoting u")
})

@Entity
@Table(name = "history_voting") //id, дата, restoran, User)
@Getter @Setter
public class HistoryVoting {

    public static final String GET_VOTING_BY_ID = "HistoryVoting.getById";
    public static final String GET_VOTING_BY_DATE_Between = "HistoryVoting.getByDate";
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

    @NotNull
    @Column(name = "isSecondVotin", nullable = false)
    private boolean isSecondVotin;

    public HistoryVoting(@NotNull Integer id,
                         @NotNull LocalDateTime dateTime,
                         @NotNull Restouran restouran,
                         @NotNull User user) {
        this.id = id;
        this.dateTime = dateTime;
        this.restouran = restouran;
        this.user = user;
        this.isSecondVotin = false;
    }

    public HistoryVoting() {
    }


    @Override
    public String toString() {
        return "HistoryVoting{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", restouran=" + restouran +
                ", user=" + user +
                ", isSecondVotin=" + isSecondVotin +
                '}';
    }

    public void setSecondVotin(boolean secondVotin) {
        isSecondVotin = secondVotin;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
