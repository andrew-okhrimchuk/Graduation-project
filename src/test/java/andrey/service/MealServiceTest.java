package andrey.service;

import andrey.to.MealTo;
import andrey.util.exception.NotEnoughRightsException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import andrey.model.Meal;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotFoundException;

import java.time.LocalDate;

import static andrey.model.AbstractBaseEntity.START_SEQ;
import static andrey.util.MealsUtil.asTo;
import static andrey.util.MealsUtil.updateFromTo;
import static org.hamcrest.core.StringContains.containsString;
import static andrey.data.MealToTestData.*;
import static andrey.data.MealToTestData.getCreated;
import static andrey.data.MealToTestData.getUpdated;
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
        assertMatch(service.getAll(REST1_id),    MEAL2, MEAL3,MEAL4,MEAL5);
    }

    @Test
    public void deleteNotEnoughRights() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotEnoughRightsException.class);
        service.delete(START_SEQ + 30);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin-4@ukr.net");
        MealTo create = getCreated();
        MealTo xx = service.create(create);
        assertMatchTo(asTo(service.get(xx.getId(), null)), xx);    }

    @Test
    public void get() throws Exception {
        Meal actual = service.get(MEAL1_ID+5, null);
        assertMatch(actual, MEAL6);
    }

    @Test
    public void getWithDate() throws Exception {
        Meal actual = service.get(MEAL1_ID+5, LocalDate.now());
        assertMatch(actual, MEAL6);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID + 13, null);
    }

    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        MealTo updated = getUpdated();
        updated.setId(MEAL1_ID);
        service.update(updated);
        assertMatchTo(asTo(service.get(MEAL1_ID, null)), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        MealTo update = getUpdated();
        update.setId(START_SEQ+28);
        update.setRestouran_id(REST3_id);
        thrown.expect(NotEnoughRightsException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_ENOUGH_RIGHTS.name()));
        thrown.expectMessage(containsString(NotEnoughRightsException.Not_Enough_Rights_exeption));
        thrown.expectMessage(containsString(String.valueOf(update)));
        service.update(update);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(REST2_id), MEALS_REST2);
    }
}