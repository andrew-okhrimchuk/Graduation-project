package andrey.service;

import andrey.model.List_of_admin;
import andrey.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static andrey.data.List_of_admin_TestData.*;
import andrey.util.exception.ErrorType;

import java.util.List;

import static andrey.data.UserTestData.*;
import static org.hamcrest.core.StringContains.containsString;


public  class List_of_AdminServiceTest extends AbstractServiceTest {

    @Autowired
    protected List_of_AdminServise service;
    @Autowired
    protected UserService userService;

    @Test
    public void get() throws Exception {
        List_of_admin actual = service.getId(REST1_id);
        assertMatch(actual, LIST1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getId(REST1_id + 100500);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(),   LIST6,LIST1, LIST2,LIST3,LIST4,LIST5);
    }

    @Test
    public void create() throws Exception {
        userService.getByEmail("admin-2@ukr.net");
        List_of_admin created = getCreated();
        assertMatch(service.getId(service.save(created).getId()),   created);
    }
   /* @Test
    public void createNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        List_of_admin created = getCreated();
        thrown.expect(NotFoundException.class);
       // thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
       // thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
       // thrown.expectMessage(containsString(String.valueOf(MEAL1_ID+50)));
        service.save(created).getId();
    }*/

    @Test
    public void delete() throws Exception {
        userService.getByEmail("admin@ukr.net");
        service.delete( LIST1.getId());
        assertMatch(service.getAll(), LIST6,LIST2,LIST3,LIST4,LIST5);
    }
    @Test
    public void deleteNotFound() throws Exception {
        userService.getByEmail("admin@ukr.net");
        thrown.expect(NotFoundException.class);
        service.delete(LIST1.getId() + 100500);
    }

    @Test
    public void getByAdminId() throws Exception {
        List<List_of_admin> actual = service.getByAdminId(ADMIN_ID);
        assertMatch(actual, list_of_admins_1);
    }

    @Test
    public void getByAdminIdNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(ADMIN_ID + 100500)));
        service.getByAdminId(ADMIN_ID + 100500);
    }

    @Test
    public void getByResrouranId() throws Exception {
        List<List_of_admin> actual = service.getByResrouranId(REST1_id);
        assertMatch(actual, list_of_admins_1);
    }

    @Test
    public void getByResrouranIdNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(REST1_id + 100500)));
        service.getByResrouranId(REST1_id + 100500);
    }


}