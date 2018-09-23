package andrey.to;

import andrey.model.HistoryVoting;
import andrey.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserTo {

    private User user;
    private HistoryVoting historyVoting;

    public UserTo(User user, HistoryVoting historyVoting) {
        this.user = user;
        this.historyVoting = historyVoting;
    }
    public void init(){
        if (historyVoting!=null){
            user.setIssecondvoitin(historyVoting.isSecondVotin());
            user.setDateVoitin(historyVoting.getDateTime());
        }
    }
    public UserTo() {
    }


}