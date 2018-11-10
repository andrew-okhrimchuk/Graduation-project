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
import static andrey.data.MealToTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static andrey.service.HistoryMealServiceImpl.NOT_EQUALS_ID;
import static andrey.service.HistoryMealServiceImpl.NOT_YESTERDAY;
import static org.hamcrest.core.StringContains.containsString;

public  class HistoryMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected HistoryMealService service;
    @Autowired
    protected UserService userService;

    @Test
    public void get() throws Exception {
        HistoryMeal actual = service.get(START_SEQ +23);
        assertMatch(actual, hM1);
    }
    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(START_SEQ);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),hM11,hM10,hM9,hM8,hM7,hM6,hM5,hM4,hM3,hM2,hM1,hM13);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin-4@ukr.net");
        HistoryMeal created = getCreated();
        int id = service.create(created, MEAL12,  500).getId();
        created.setId(id);
        assertMatch(service.get(MEAL12.getId()),   created);
    }

    /*@Test
    public void createNotFoundMeal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal created = getCreated();
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(START_SEQ + 50)));
        service.create(created, MEAL_Not_Exist,  500).getId();

    }
*/
    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal updated = getUpdated();
 //       int id = service.update(updated, MEAL1_ID + 10,  500).getId();
//        assertMatch(service.get(id), updated);
    }

    @Test
    public void updateNotFoundMeal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal updated = getUpdated();
        updated.setDate(LocalDate.now());
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(NOT_EQUALS_ID)));
        service.update(updated, MEAL_Not_Exist,  500).getId();
    }

    @Test
    public void updateNotFound_H_Meal() throws Exception {
        userService.getByEmail("admin@ukr.net");
        HistoryMeal updated = getUpdated();
        updated.setId(HM_ID + 80);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(NOT_YESTERDAY)));
        service.update(updated, MEAL12 ,  500).getId();
    }


}