package andrey.service;

import andrey.model.HistoryVoting;
import andrey.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static andrey.data.HistoryVotingTestData.*;

public  class HistoryVotingServiceTest extends AbstractServiceTest {

    @Autowired
    protected HistoryVotingService service;
    @Autowired
    protected UserService userService;

    @Test
    public void get() throws Exception {
        HistoryVoting actual = service.get(HV_ID + 20);
        assertMatch(actual, hV3);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(HV_ID + 25);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),   hV1,hV2,hV3,hV4,hV5);
    }
/*
    @Test
    public void create() throws Exception {
        userService.getByEmail("admin-4@ukr.net");
        HistoryVoting created = getCreated();
        int id = service.save_a_vote(created, REST7_id,  USER_ID + 1).getId();
        assertMatch(service.get(id),   created);
    }

    @Test
    public void createNotFoundMeal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryVoting created = getCreated();
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(MEAL1_ID+50)));
        service.create(created, MEAL1_ID + 50,  500, ADMIN_ID).getId();
    }

    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryVoting updated = getUpdated();
        int id = service.update(updated, MEAL1_ID + 10,  500, ADMIN_ID).getId();
        assertMatch(service.get(id), updated);
    }

    @Test
    public void updateNotFoundMeal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryVoting updated = getUpdated();
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(MEAL1_ID+50)));
        service.update(updated, MEAL1_ID + 50,  500, ADMIN_ID).getId();
    }

    @Test
    public void updateNotFound_H_Meal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryVoting updated = getUpdated();
        updated.setId(14);
        thrown.expect(NotFoundException.class);
      //  thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
     //   thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
     //   thrown.expectMessage(containsString(String.valueOf(MEAL1_ID)));
        System.out.println("55555" + service.update(updated, MEAL1_ID ,  500, ADMIN_ID).getId());
    }


    @Test
    public void delete() throws Exception {
        userService.getByEmail("admin@ukr.net");
        service.delete(HM_ID + 1, ADMIN_ID); //int historyMeal_id, int user_id
        assertMatch(service.getAll(), hM11,hM10,hM9,hM8,hM7,hM6,hM5,hM4,hM3,hM2);
    }
    @Test
    public void deleteNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID + 25, ADMIN_ID);
    }
*/

}