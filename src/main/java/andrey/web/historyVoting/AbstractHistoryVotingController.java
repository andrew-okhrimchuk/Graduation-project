package andrey.web.historyVoting;

import andrey.AuthorizedUser;
import andrey.model.HistoryVoting;
import andrey.model.Meal;
import andrey.service.HistoryVotingService;
import andrey.service.MealService;
import andrey.to.MealTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static andrey.util.ValidationUtil.assureIdConsistent;
import static andrey.util.ValidationUtil.checkNew;


public abstract class AbstractHistoryVotingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private HistoryVotingService service;


    public HistoryVoting getToday() {
        log.info("get Voting today = {} ", LocalDate.now());
        return service.getToday();
    }

    public HistoryVoting getByDate (LocalDate date) {
        log.info("get Voting {} ", date);
        return service.getByDate(date);
    }

    public HistoryVoting createVoting(int restouran_id) {
        int userId = AuthorizedUser.id();
        log.info("create Voting today with restouran id = {}, and user {}", restouran_id, userId);
        return service.save_a_vote(restouran_id, userId);
    }
}