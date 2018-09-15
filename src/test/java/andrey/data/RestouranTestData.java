package andrey.data;

import andrey.model.Role;
import andrey.model.User;
import andrey.model.Restouran;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class RestouranTestData {
    public static final int Restouran_ID = START_SEQ + 9;

    public static final Restouran REST1 = new Restouran(1, "Катруся");
    public static final Restouran REST2 = new Restouran(2, "Клубничка");

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals", "password").isEqualTo(expected);
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