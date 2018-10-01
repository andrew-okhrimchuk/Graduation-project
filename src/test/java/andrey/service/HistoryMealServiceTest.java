package andrey.service;

import andrey.model.HistoryMeal;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static andrey.data.HistoryMealTestData.*;
import static andrey.data.HistoryMealTestData.assertMatch;
import static andrey.data.HistoryMealTestData.getCreated;
import static andrey.data.HistoryMealTestData.getUpdated;
import static andrey.data.MealTestData.MEAL12;
import static andrey.data.MealTestData.*;
import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.data.UserTestData.USER_ID;
import static org.hamcrest.core.StringContains.containsString;

public  class HistoryMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected HistoryMealService service;
    @Autowired
    protected UserService userService;

    @Test
    public void get() throws Exception {
        HistoryMeal actual = service.get(HM_ID + 1);
        assertMatch(actual, hM1);
    }
    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(HM_ID + 25);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), hM11,hM10,hM9,hM8,hM7,hM6,hM5,hM4,hM3,hM2,hM1);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal created = getCreated();
        int id = service.create(created, MEAL1_ID + 11,  500, ADMIN_ID).getId();
        assertMatch(service.get(id),   created);
    }

    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal updated = getUpdated();
        int id = service.update(updated, MEAL1_ID + 10,  500, ADMIN_ID).getId();
        assertMatch(service.get(id), updated);
    }

  /*  @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(MEAL1_ID)));
        service.update(MEAL1, ADMIN_ID);
    }*/


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

}