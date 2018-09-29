package andrey.data;

import static andrey.data.MealTestData.*;

import andrey.model.Meal;
import andrey.model.User;
import andrey.model.Restouran;


import java.util.*;

import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestouranTestData {
    public static final int Restouran_ID = START_SEQ + 9;

    public static final int REST1_id = 1;
    public static final int REST2_id = 2;
    public static final int REST3_id = 3;
    public static final int REST4_id = 4;
    public static final int REST5_id = 5;
    public static final int REST6_id = 6;
    public static final int REST7_id = 7;


    public static final Restouran REST1 = new Restouran(REST1_id, "Катруся");
    public static final Restouran REST2 = new Restouran(REST2_id, "Клубничка");
    public static final Restouran REST3 = new Restouran(REST3_id, "Нака-ти-ка");
    public static final Restouran REST4 = new Restouran(REST4_id, "Спотыкач");
    public static final Restouran REST5 = new Restouran(REST5_id, "У Семеныча");
    public static final Restouran REST6 = new Restouran(REST6_id, "Вареничная");
    public static final Restouran REST7 = new Restouran(REST7_id, "Not Found");

    public static  List<Restouran> list = Arrays.asList(REST1,REST2,REST3,REST4,REST5,REST6);

    public static void init () {
        REST2.setMeals(Arrays.asList(MEAL7,MEAL8,MEAL9));
    }
    public static Restouran getCreated() {
        return new Restouran(null,  "Калинка-Малинка");
    }

    public static Restouran getUpdated() {
        return new Restouran(REST1_id,  "Убгрейд");
    }

    public static Restouran getRestouranByID(int restouran_ID) {
        return list.get(restouran_ID-1);
    }

    public static void assertMatch(Restouran actual, Restouran expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meals", "list_of_admin");
    }

    public static void assertMatch(Iterable<Restouran> actual, Restouran... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<Restouran> actual, Iterable<Restouran> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals", "list_of_admin").isEqualTo(expected);
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