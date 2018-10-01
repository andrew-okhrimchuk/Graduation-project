package andrey.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import andrey.model.Meal;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotFoundException;

import static org.hamcrest.core.StringContains.containsString;
import static andrey.data.MealTestData.getCreated;
import static andrey.data.MealTestData.getUpdated;
import static andrey.data.MealTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.data.RestouranTestData.*;

public  class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;


    @Test
    public void delete() throws Exception {
        service.delete(MEAL1_ID, REST1_id);
        assertMatch(service.getAll(REST1_id),    MEAL3,MEAL2,MEAL4,MEAL5);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID, REST2_id);
    }

    @Test
    public void create() throws Exception {
        Meal created = getCreated();
        service.create(created, REST2_id);
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
        Meal updated = getUpdated(); //return new Meal(MEAL1_ID,  "Печень по-грузински, с черносивом", REST1);
        service.update(updated, REST1_id);
        assertMatch(service.get(MEAL1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(MEAL1_ID)));
        service.update(MEAL1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(REST2_id), MEALS_REST2);
    }
}