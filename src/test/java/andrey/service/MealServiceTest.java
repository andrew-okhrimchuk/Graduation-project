package andrey.service;

import andrey.util.exception.NotEnoughRightsException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import andrey.model.Meal;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotFoundException;

import static org.hamcrest.core.StringContains.containsString;
import static andrey.data.MealTestData.getCreated;
import static andrey.data.MealTestData.getUpdated;
import static andrey.data.MealTestData.*;
import static andrey.data.RestouranTestData.*;

public  class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;
    @Autowired
    protected UserService userService;

    @Test
    public void delete() throws Exception {
        userService.getByEmail("admin@ukr.net");
        service.delete(MEAL1_ID);
        assertMatch(service.getAll(REST1_id),    MEAL3,MEAL2,MEAL4,MEAL5);
    }

    @Test
    public void deleteNotEnoughRights() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotEnoughRightsException.class);
        service.delete(MEAL1_ID+5);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin-3@ukr.net");
        Meal created = getCreated();
        service.create(created);
        assertMatch(service.getAll(REST2_id),   MEAL7,created,MEAL8,MEAL9);
    }

    @Test
    public void get() throws Exception {
        Meal actual = service.get(MEAL1_ID+5);
        assertMatch(actual, MEAL6);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID + 13);
    }

    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        Meal updated = getUpdated(); //return new Meal(MEAL1_ID,  "Печень по-грузински, с черносивом", REST1);
        service.update(updated);
        assertMatch(service.get(MEAL1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotEnoughRightsException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_ENOUGH_RIGHTS.name()));
        thrown.expectMessage(containsString(NotEnoughRightsException.Not_Enough_Rights_exeption));
        thrown.expectMessage(containsString(String.valueOf(MEAL6)));
        service.update(MEAL6);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(REST2_id), MEALS_REST2);
    }
}