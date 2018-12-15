package andrey.to;

import andrey.model.HistoryVoting;
import andrey.model.User;
import lombok.Getter;
import lombok.Setter;


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

    public UserToDb() {
    }


}