package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_voting")
public class HistoryVoting {

    @NotNull
    @Id
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restouran_id")
    private Restouran restouran;

    @NotNull
    @Column(name = "countVoting", nullable = false)
    private int countVoting;

    public HistoryVoting(@NotNull LocalDateTime dateTime, @NotNull Restouran restouran, @NotNull int countVoting) {
        this.dateTime = dateTime;
        this.restouran = restouran;
        this.countVoting = countVoting;
    }

    public HistoryVoting() {
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

    public int getCountVoting() {
        return countVoting;
    }

    @Override
    public String toString() {
        return "HistoryVoting{" +
                "dateTime=" + dateTime +
                ", restouran=" + restouran +
                ", countVoting=" + countVoting +
                '}';
    }

    public void setCountVoting(int countVoting) {
        this.countVoting = countVoting;
    }
}
