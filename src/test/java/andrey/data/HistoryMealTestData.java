package andrey.data;

import andrey.model.HistoryMeal;

import java.time.LocalDate;
import java.util.Arrays;

import static andrey.data.MealToTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class HistoryMealTestData {
    public static final int HM_ID = START_SEQ;

    public static final HistoryMeal hM1 = new HistoryMeal(HM_ID + 35, MEAL1, LocalDate.now(),1500); //100002
    public static final HistoryMeal hM2 = new HistoryMeal(HM_ID + 36, MEAL2, LocalDate.now(),1800);
    public static final HistoryMeal hM3 = new HistoryMeal(HM_ID + 37, MEAL3, LocalDate.now(),120);
    public static final HistoryMeal hM4 = new HistoryMeal(HM_ID + 38, MEAL4, LocalDate.now(),2320);
    public static final HistoryMeal hM5 = new HistoryMeal(HM_ID + 39, MEAL5, LocalDate.now(),2400);
    public static final HistoryMeal hM6 = new HistoryMeal(HM_ID + 40, MEAL6, LocalDate.now(),5300);
    public static final HistoryMeal hM7 = new HistoryMeal(HM_ID + 41, MEAL7, LocalDate.now(),1510); //100008
    public static final HistoryMeal hM8 = new HistoryMeal(HM_ID + 42, MEAL8, LocalDate.now(),1490);
    public static final HistoryMeal hM9 = new HistoryMeal(HM_ID + 43, MEAL9, LocalDate.now(),1490);//100010
    public static final HistoryMeal hM10 = new HistoryMeal(HM_ID + 44, MEAL10, LocalDate.now(),1490);
    public static final HistoryMeal hM11 = new HistoryMeal(HM_ID + 45, MEAL11, LocalDate.now(),1490);
    public static final HistoryMeal hM12 = new HistoryMeal(HM_ID + 47, MEAL12, LocalDate.now(),1490);
    public static final HistoryMeal hM13 = new HistoryMeal(HM_ID + 46, MEAL1, LocalDate.of(2018, 9,21),1490);


    public static HistoryMeal getCreated() {
        return new HistoryMeal();
    }

    public static HistoryMeal getUpdated() {
        return new HistoryMeal(HM_ID + 47, MEAL12, LocalDate.now().minusDays(1),2000);
    }


    public static void assertMatch(HistoryMeal actual, HistoryMeal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meal");
    }

    public static void assertMatch(Iterable<HistoryMeal> actual, HistoryMeal... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<HistoryMeal> actual, Iterable<HistoryMeal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meal").isEqualTo(expected);
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