package andrey.data;

import andrey.model.Role;
import andrey.model.User;
import org.springframework.test.web.servlet.ResultMatcher;
import java.util.*;

import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static Set<Role> roles;
    static {roles.add(Role.ROLE_USER);
            roles.add(Role.ROLE_ADMIN);}

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Collections.singleton(Role.ROLE_USER));
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", roles);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateVoitin", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password", "dateVoitin").isEqualTo(expected);
    }
/*
    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "registered", "password"));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    */
}