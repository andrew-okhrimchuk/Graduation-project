package andrey.service;

import andrey.model.Meal;
import andrey.model.Restouran;
import andrey.util.exception.ErrorType;
import andrey.util.exception.NotEnoughRightsException;
import andrey.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        service.delete(REST4_id, USER_ID+3);
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
//список админов на не существующий ресторан пуст! проверку отменить
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
        Restouran updated = getUpdated(); // REST1_id,  "Убгрейд");
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
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_Enough_Rights.name()));
        thrown.expectMessage(containsString(NotEnoughRightsException.NOT_Enough_Rights_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(REST6_id)));
        service.update(REST6, ADMIN_ID);
    }
    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),    REST5,REST4,REST3,REST2,REST1,REST6);
    }
/*
    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void testValidation() throws Exception {
        Assume.assumeTrue(isJpaBased());
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "  ", 300), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, null, "Description", 300), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 9), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 5001), USER_ID), ConstraintViolationException.class);
    }

    */
}