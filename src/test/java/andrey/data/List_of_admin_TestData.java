package andrey.data;

import andrey.model.List_of_admin;
import andrey.model.Restouran;
import andrey.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static andrey.data.MealTestData.*;
import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class List_of_admin_TestData {

    public static final int REST1_id = 1;
    public static final int REST2_id = 2;
    public static final int REST3_id = 3;
    public static final int REST4_id = 4;
    public static final int REST5_id = 5;
    public static final int REST6_id = 6;


    public static final List_of_admin LIST1 = new List_of_admin(1, REST1, ADMIN_2);
    public static final List_of_admin LIST2 = new List_of_admin(2, REST2, ADMIN_4);
    public static final List_of_admin LIST3 = new List_of_admin(3, REST3, ADMIN_5);
    public static final List_of_admin LIST4 = new List_of_admin(4, REST4, ADMIN_6);
    public static final List_of_admin LIST5 = new List_of_admin(5, REST5, ADMIN_6);
    public static final List_of_admin LIST6 = new List_of_admin(6, REST6, ADMIN_6);

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