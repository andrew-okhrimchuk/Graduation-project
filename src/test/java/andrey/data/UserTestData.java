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
    public static final int ADMIN_ID = START_SEQ+2;// = 2
    public static final int USER_ID = START_SEQ+3; // = 3
    public static Set<Role> roles = new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));

    public static final User ADMIN_2 = new User(ADMIN_ID, "Admin", "admin@ukr.net", "admin", roles);
    public static final User USER_3 = new User(USER_ID, "User-1", "user-1@ukr.net", "user-1", Collections.singleton(Role.ROLE_USER));
    public static final User ADMIN_4 = new User(USER_ID+1, "Admin-3", "admin-3@ukr.net", "admin-3", roles);
    public static final User ADMIN_5 = new User(USER_ID+2, "Admin-4", "admin-4@ukr.net", "admin-4", roles);
    public static final User ADMIN_6 = new User(USER_ID+3, "Admin-2", "admin-2@ukr.net", "admin-2", roles);
    public static final User USER_7 = new User(USER_ID+4, "User-2", "user-2@ukr.net", "user-2", Collections.singleton(Role.ROLE_USER));
  //  public static final User USER_8 = new User(USER_ID+5, "TEST", "userTEST@ukr.net", "test123", Collections.singleton(Role.ROLE_USER));

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