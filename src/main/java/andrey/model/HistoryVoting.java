package andrey.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_ID, query = "SELECT u FROM HistoryVoting u WHERE u.id=:id"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE_Between, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime>=:start AND u.dateTime<=:end ORDER BY u.dateTime DESC"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_DATE_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.dateTime>=:dateTimeStart AND u.dateTime<=:dateTimeEnd AND u.user.id=:userId"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_RESTOURAN_ID, query = "SELECT u FROM HistoryVoting u WHERE u.restouran=:restouran"),
        @NamedQuery(name = HistoryVoting.GET_VOTING_BY_USER_ID, query = "SELECT u FROM HistoryVoting u WHERE u.user=:userId"),
        @NamedQuery(name = HistoryVoting.GET_All, query = "SELECT u FROM HistoryVoting u")
})

@Entity
@Table(name = "history_voting") //id, дата, restoran, User)
@Getter @Setter @ToString
public class HistoryVoting extends AbstractBaseEntity {

    public static final String GET_VOTING_BY_ID = "HistoryVoting.getById";
    public static final String GET_VOTING_BY_DATE_Between = "HistoryVoting.getByDate";
    public static final String GET_VOTING_BY_DATE_BY_USER_ID = "HistoryVoting.getByDateByUserId";
    public static final String GET_VOTING_BY_RESTOURAN_ID = "HistoryVoting.getByRestouranId";
    public static final String GET_VOTING_BY_USER_ID = "HistoryVoting.getByUserId";
    public static final String GET_All = "HistoryVoting.getAll";


    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restouran_id", nullable = false)
    private Restouran restouran;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "isSecondVotin", nullable = false)
    private boolean isSecondVotin;

    public HistoryVoting(Integer id, @NotNull LocalDate dateTime, Restouran restouran, @NotNull User user) {
        super(id);
        this.dateTime = dateTime;
        this.restouran = restouran;
        this.user = user;
    }

    public HistoryVoting() {
    }

    public void setSecondVotin(boolean secondVotin) {
        isSecondVotin = secondVotin;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
