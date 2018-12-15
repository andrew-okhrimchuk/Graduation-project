package andrey.web.historyVoting;

import andrey.AuthorizedUser;
import andrey.model.HistoryVoting;
import andrey.service.HistoryVotingService;
import andrey.util.threadLocal.ServiceBin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;



public abstract class AbstractHistoryVotingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private  HistoryVotingService service;
    @Autowired
    private  ServiceBin serviceBin;



    public List<HistoryVoting>  getToday() {
        serviceBin.set_ThreadLoca_to_Bin();
        log.info("get Voting today = {} ", LocalDate.now());
        return service.getToday();
    }

    public List<HistoryVoting>  getByDate (LocalDate date) {
        serviceBin.set_ThreadLoca_to_Bin();
        log.info("get Voting {} ", date);
        return service.getByDate(date);
    }

    public HistoryVoting createVoting(int restouran_id) {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        log.info("create Voting today with restouran id = {}, and user {}", restouran_id, userId);
        return service.save_a_vote(restouran_id, userId);
    }
}