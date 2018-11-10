package andrey.data;

import andrey.model.List_of_admin;

import java.util.Arrays;
import java.util.List;

import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class List_of_admin_TestData {

    public static final int REST1_id = START_SEQ + 6;
    public static final int REST2_id = START_SEQ + 7;
    public static final int REST3_id = START_SEQ + 8;
    public static final int REST4_id = START_SEQ + 9;
    public static final int REST5_id = START_SEQ + 10;
    public static final int REST6_id = START_SEQ + 11;


    public static final List_of_admin LIST1 = new List_of_admin(START_SEQ+12, REST1, ADMIN);
    public static final List_of_admin LIST2 = new List_of_admin(START_SEQ+13, REST2, ADMIN_4);
    public static final List_of_admin LIST3 = new List_of_admin(START_SEQ+14, REST3, ADMIN_5);
    public static final List_of_admin LIST4 = new List_of_admin(START_SEQ+15, REST4, ADMIN_6);
    public static final List_of_admin LIST5 = new List_of_admin(START_SEQ+16, REST5, ADMIN_6);
    public static final List_of_admin LIST6 = new List_of_admin(START_SEQ+17, REST6, ADMIN_6);

    public static List<List_of_admin> list_of_admins_1 = Arrays.asList(LIST1);

    public static List_of_admin getCreated() {
        return new List_of_admin(null,  REST6, ADMIN_5);
    }

    public static void assertMatch(List_of_admin actual, List_of_admin expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "");
    }

    public static void assertMatch(Iterable<List_of_admin> actual, List_of_admin... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<List_of_admin> actual, Iterable<List_of_admin> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
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