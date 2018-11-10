package andrey.web;

import andrey.data.UserTestData;
import andrey.model.User;
import andrey.repository.mock.InMemoryUserRepositoryImpl;
import andrey.util.exception.NotFoundException;
import andrey.web.user.AdminRestController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static andrey.data.UserTestData.ADMIN;
import java.util.Collection;




@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
        repository.init();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}
