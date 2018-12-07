package andrey.service;

import andrey.model.HistoryVoting;

import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.ADMIN_ID;
import static andrey.data.UserTestData.USER_3;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static andrey.service.HistoryVotingServiceImpl.*;
import andrey.util.exception.AlreadyVotedException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static andrey.data.HistoryVotingTestData.*;
import static andrey.data.HistoryVotingTestData.getCreated;
import static andrey.data.UserTestData.USER_ID;

public  class HistoryVotingServiceTest extends AbstractServiceTest {

    @Autowired
    protected HistoryVotingService service;
    @Autowired
    protected UserService userService;

    @Test
    public void getToday() throws Exception {
        List<HistoryVoting>  actual = service.getToday();
        assertMatch(actual, hV4,hV3,hV2,hV1);
    }
    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),   hV1,hV2,hV3,hV4,hV5);
    }
    @Test
    public void create() throws Exception {
        userService.getByEmail("user-2@ukr.net");
        HistoryVoting created = getCreated();
        if (LocalTime.now().getHour() < LocalTime.of(11, 00).getHour()) {
            created.setId(service.save_a_vote(REST4_id,  USER_ID + 4).getId());
            assertMatch(service.getToday(),   created,hV4,hV3,hV2,hV1);
        }
        else votindAfter11AM ();
    }
    @Test
    public void createDataIntegrityViolationException() throws Exception {
        userService.getByEmail("user-2@ukr.net");
        if (LocalTime.now().getHour() < LocalTime.of(11, 00).getHour()) {
            thrown.expect(DataIntegrityViolationException.class);
            service.save_a_vote(REST7_id, USER_ID + 4);
        } else votindAfter11AM ();
    }
    @Test
    public void createSecoundVoting() throws Exception {
        userService.getByEmail("user-1@ukr.net");
        HistoryVoting created = new HistoryVoting(START_SEQ+19, USER_ID, LocalDate.now(), REST2 );
        created.setSecondVotin(true);
        created.setId(service.save_a_vote(REST2.getId(),  USER_ID).getId());

        assertMatch(service.getToday(),   hV4,hV3,hV1);
    }
    @Test
    public void getByDate() throws Exception {
        List<HistoryVoting> actual = service.getByDate(LocalDate.now());
        assertMatch(actual, hV4,hV3,hV2,hV1);
    }

    private void votindAfter11AM (){
            thrown.expect(AlreadyVotedException.class);
            thrown.expectMessage(massageMore11AM);
            service.save_a_vote(REST4_id,  USER_ID + 4);
    }
}