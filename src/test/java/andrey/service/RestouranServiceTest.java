package andrey.service;

import andrey.model.Restouran;
import andrey.to.MealMenu;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotEnoughRightsException;
import andrey.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static org.hamcrest.core.StringContains.containsString;

public  class RestouranServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestouranService service;
    @Autowired
    protected UserService userService;


    @Test
    public void delete() throws Exception {
        userService.getByEmail("admin-2@ukr.net");
        service.delete(REST4_id, USER_ID + 3);
        assertMatch(service.getAll(),    REST5,REST3,REST2, REST1,REST6);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(REST7_id, USER_ID);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin@ukr.net");
        Restouran created = getCreated();
        service.create(created, ADMIN_ID);
        assertMatch(service.getAll(),   REST5,REST4,REST3,REST2, REST1,created,REST6);
    }

    @Test
    public void get() throws Exception {
        Restouran actual = service.get(REST1_id, ADMIN_ID);
        assertMatch(actual, REST1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(REST7_id, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        userService.getByEmail("admin@ukr.net");
        Restouran updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(REST1_id, ADMIN_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(ADMIN_ID)));
        service.update(REST7, ADMIN_ID);
    }
    @Test
    public void updateNotEnoughRightsException() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotEnoughRightsException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_ENOUGH_RIGHTS.name()));
        thrown.expectMessage(containsString(NotEnoughRightsException.Not_Enough_Rights_exeption));
        thrown.expectMessage(containsString(String.valueOf(REST6_id)));
        service.update(REST6, ADMIN_ID);
    }
    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),    REST5,REST4,REST3,REST2,REST1,REST6);
    }

    @Test
    public void getManuToday() throws Exception {
        List<MealMenu> actual = service.getManuToday();
        assertMatch(actual, mealMenu1,mealMenu2,mealMenu3,mealMenu4,mealMenu5,mealMenu6,mealMenu7,mealMenu8,mealMenu9,mealMenu10,mealMenu11);
    }
}