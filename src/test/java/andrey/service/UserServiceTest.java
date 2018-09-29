package andrey.service;

import andrey.util.exception.ErrorType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import andrey.model.Role;
import andrey.model.User;
import andrey.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static andrey.data.UserTestData.USER_ID;
import static andrey.data.UserTestData.*;
import static org.hamcrest.core.StringContains.containsString;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null,"New" , "new@gmail.com", "newPass", Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN_2, ADMIN_6,ADMIN_4,ADMIN_5, newUser, USER_3, USER_7);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null,"Dublicate","user-1@ukr.net", "user-1",  Collections.singleton(Role.ROLE_USER)));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN_2, ADMIN_6,ADMIN_4,ADMIN_5, USER_7);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN_2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("admin@ukr.net");
        assertMatch(user, ADMIN_2);
    }
    @Test
    public void getByEmail_1() throws Exception {
        User user = service.getByEmail("admin-4@ukr.net");
        assertMatch(user, ADMIN_5);
    }
    @Test
    public void getByEmail_2() throws Exception {
        User user = service.getByEmail("admin-2@ukr.net");
        assertMatch(user, ADMIN_6);
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() throws Exception {
        service.getByEmail("qqqqq");
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER_3);
        updated.setName("UpdatedName");
        updated.setRoles(roles);
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN_2, ADMIN_6,ADMIN_4,ADMIN_5, USER_3, USER_7);
    }
}