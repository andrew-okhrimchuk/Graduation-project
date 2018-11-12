package andrey.to;

import andrey.model.HistoryVoting;
import andrey.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserToDb {

    private User user;
    private HistoryVoting historyVoting;


    public UserToDb(User user, HistoryVoting historyVoting) {
        this.user = user;
        this.historyVoting = historyVoting;
    }
    public void init(){
        if (historyVoting!=null ){
            user.setHistoryVoting(historyVoting);
        }
    }

   /* public UserToDb(User user, LocalDate date, boolean isSecondVotin) {
        this.user = user;
        this.date = date;
        this.isSecondVotin = isSecondVotin;
    }
    public void init(){
        if (date!=null && date == LocalDate.now()){
            user.setDateVoitin(date);
        }
        user.setIssecondvoitin(isSecondVotin);


    }*/
    public UserToDb() {
    }


}