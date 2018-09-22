package andrey.data;

import andrey.model.Meal;
import java.util.Arrays;
import java.util.List;

import static andrey.data.RestouranTestData.*;
import static andrey.data.HistoryMealTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static andrey.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 2;

    public static final Meal MEAL1 = new Meal(MEAL1_ID,  "Печень по-грузински", REST1);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1,  "Пахлава", REST1);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2,  "Печень по-арабски", REST1);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, "Лапша нарезная", REST1);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4,"Борщ", REST1);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5,  "Вареники", REST3);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6,  "Печень по-арабски", REST2);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7,  "Восточные сладости", REST2);
    public static final Meal MEAL9 = new Meal(MEAL1_ID + 8,  "Барашек по-армянски", REST2);
    public static final Meal MEAL10 = new Meal(MEAL1_ID + 9,  "Ножки лягушки тушеные", REST3);
    public static final Meal MEAL11 = new Meal(MEAL1_ID + 10,  "Ля-Суп", REST3);
    public static final Meal MEAL12 = new Meal(MEAL1_ID + 11,  "Суп по-французски", REST3);

    public static final List<Meal> MEALS = Arrays.asList(MEAL8, MEAL7,MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    public static final List<Meal> MEALS_REST2 = Arrays.asList(MEAL7, MEAL8,MEAL9);

    public static Meal getCreated() {
        return new Meal(null,  "Мамалыга", REST2);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID,  "Печень по-грузински, с черносивом", REST1);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "cost");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restouran",  "cost").isEqualTo(expected);
    }
    public static void init(){
        MEAL1.setCost(hM1.getCost());
        MEAL2.setCost(hM2.getCost());
        MEAL3.setCost(hM3.getCost());
        MEAL4.setCost(hM4.getCost());
        MEAL5.setCost(hM5.getCost());
        MEAL6.setCost(hM6.getCost());
        MEAL7.setCost(hM7.getCost());
        MEAL8.setCost(hM8.getCost());
        MEAL9.setCost(hM9.getCost());
        MEAL10.setCost(hM10.getCost());
        MEAL11.setCost(hM11.getCost());
        MEAL12.setCost(hM12.getCost());
    }
}
