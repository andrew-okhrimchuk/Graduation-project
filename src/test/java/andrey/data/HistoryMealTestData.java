package andrey.data;

import andrey.model.HistoryMeal;

import java.time.LocalDate;
import java.util.Arrays;

import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.data.MealTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class HistoryMealTestData {

    public static final HistoryMeal hM1 = new HistoryMeal(1, MEAL1, LocalDate.now(),1500);
    public static final HistoryMeal hM2 = new HistoryMeal(2, MEAL2, LocalDate.now(),1800);
    public static final HistoryMeal hM3 = new HistoryMeal(3, MEAL3, LocalDate.now(),120);
    public static final HistoryMeal hM4 = new HistoryMeal(4, MEAL4, LocalDate.now(),2320);
    public static final HistoryMeal hM5 = new HistoryMeal(5, MEAL5, LocalDate.now(),2400);
    public static final HistoryMeal hM6 = new HistoryMeal(6, MEAL6, LocalDate.now(),5300);
    public static final HistoryMeal hM7 = new HistoryMeal(7, MEAL7, LocalDate.now(),1510);
    public static final HistoryMeal hM8 = new HistoryMeal(8, MEAL8, LocalDate.now(),1490);
    public static final HistoryMeal hM9 = new HistoryMeal(9, MEAL9, LocalDate.now(),1490);
    public static final HistoryMeal hM10 = new HistoryMeal(10, MEAL10, LocalDate.now(),1490);
    public static final HistoryMeal hM11 = new HistoryMeal(11, MEAL11, LocalDate.now(),1490);
    public static final HistoryMeal hM12 = new HistoryMeal(12, MEAL12, LocalDate.now(),1490);



    public static void assertMatch(HistoryMeal actual, HistoryMeal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "");
    }

    public static void assertMatch(Iterable<HistoryMeal> actual, HistoryMeal... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<HistoryMeal> actual, Iterable<HistoryMeal> expected) {
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