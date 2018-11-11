package andrey.data;

import static andrey.data.MealToTestData.*;

import andrey.model.Restouran;
import andrey.to.MealMenu;
import andrey.to.RestouranTo;


import java.util.*;

import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestouranTestData {
    public static final int Restouran_ID = START_SEQ ;

    public static final int REST1_id = START_SEQ + 6;
    public static final int REST2_id = START_SEQ + 7;
    public static final int REST3_id = START_SEQ + 8;
    public static final int REST4_id = START_SEQ + 9;
    public static final int REST5_id = START_SEQ + 10;
    public static final int REST6_id = START_SEQ + 11;
    public static final int REST7_id = START_SEQ + 12;


    public static final Restouran REST1 = new Restouran(REST1_id, "Катруся");
    public static final Restouran REST2 = new Restouran(REST2_id, "Клубничка");
    public static final Restouran REST3 = new Restouran(REST3_id, "Нака-ти-ка");
    public static final Restouran REST4 = new Restouran(REST4_id, "Спотыкач");
    public static final Restouran REST5 = new Restouran(REST5_id, "У Семеныча");
    public static final Restouran REST6 = new Restouran(REST6_id, "Вареничная");
    public static final Restouran REST7 = new Restouran(REST7_id, "Not Found");
    public static  List<Restouran> list = Arrays.asList(REST1,REST2,REST3,REST4,REST5,REST6);

    public static MealMenu mealMenu1 ;
    public static  MealMenu mealMenu2 ;
    public static  MealMenu mealMenu3 ;
    public static  MealMenu mealMenu4 ;
    public static  MealMenu mealMenu5 ;
    public static  MealMenu mealMenu6 ;
    public static  MealMenu mealMenu7 ;
    public static  MealMenu mealMenu8 ;
    public static  MealMenu mealMenu9 ;
    public static  MealMenu mealMenu10 ;
    public static  MealMenu mealMenu11 ;


    public static void init () {
        REST2.setMeals(Arrays.asList(MEAL7,MEAL8,MEAL9));
        mealMenu1 = new MealMenu(REST1.getName(), MEAL1.getName(),MEAL1.getCost());
        mealMenu2 = new MealMenu(REST1.getName(), MEAL2.getName(),MEAL2.getCost());
        mealMenu3 = new MealMenu(REST1.getName(), MEAL3.getName(),MEAL3.getCost());
        mealMenu4 = new MealMenu(REST1.getName(), MEAL4.getName(),MEAL4.getCost());
        mealMenu5 = new MealMenu(REST1.getName(), MEAL5.getName(),MEAL5.getCost());
        mealMenu6 = new MealMenu(REST2.getName(), MEAL7.getName(),MEAL7.getCost());
        mealMenu7 = new MealMenu(REST2.getName(), MEAL8.getName(),MEAL8.getCost());
        mealMenu8 = new MealMenu(REST2.getName(), MEAL9.getName(),MEAL9.getCost());
        mealMenu9 = new MealMenu(REST3.getName(), MEAL6.getName(),MEAL6.getCost());
        mealMenu10 = new MealMenu(REST3.getName(), MEAL10.getName(),MEAL10.getCost());
        mealMenu11 = new MealMenu(REST3.getName(), MEAL11.getName(),MEAL11.getCost());
    }


    public static Restouran getCreated() {
        return new Restouran(null,  "Калинка-Малинка");
    }
    public static Restouran getUpdated() {
        return new Restouran(REST1_id,  "Убгрейд");
    }
    public static RestouranTo getUpdatedTo() {
        return new RestouranTo(REST1_id,  "УбгрейдTo");
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



    public static void assertMatch(MealMenu actual, MealMenu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "");
    }
    public static void assertMatch(Iterable<MealMenu> actual, MealMenu... expected) {
        assertMatch1(actual, Arrays.asList(expected));

    }
    public static void assertMatch1(Iterable<MealMenu> actual, Iterable<MealMenu> expected) {
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