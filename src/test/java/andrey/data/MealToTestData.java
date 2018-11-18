package andrey.data;

import andrey.model.Meal;
import andrey.to.MealTo;

import java.util.Arrays;
import java.util.List;

import static andrey.data.RestouranTestData.*;
import static andrey.data.HistoryMealTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static andrey.model.AbstractBaseEntity.START_SEQ;

public class MealToTestData {
    public static final int MEAL1_ID = START_SEQ + 23;

    public static final Meal MEAL1 = new Meal(START_SEQ + 23,  "Печень по-грузински", REST1);
    public static final Meal MEAL2 = new Meal(START_SEQ + 24,  "Пахлава", REST1);
    public static final Meal MEAL3 = new Meal(START_SEQ + 25,  "Печень по-арабски", REST1);
    public static final Meal MEAL4 = new Meal(START_SEQ + 26,  "Лапша нарезная", REST1);
    public static final Meal MEAL5 = new Meal(START_SEQ + 27,  "Борщ", REST1);
    public static final Meal MEAL6 = new Meal(START_SEQ + 28,  "Вареники", REST3);
    public static final Meal MEAL7 = new Meal(START_SEQ + 29,  "Печень по-арабски", REST2);
    public static final Meal MEAL8 = new Meal(START_SEQ + 30,  "Восточные сладости", REST2);
    public static final Meal MEAL9 = new Meal(START_SEQ + 31, "Барашек по-армянски", REST2);
    public static final Meal MEAL10 = new Meal(START_SEQ + 32,"Ножки лягушки тушеные", REST3);
    public static final Meal MEAL11 = new Meal(START_SEQ + 33,"Ля-Суп", REST3);
    public static final Meal MEAL12 = new Meal(START_SEQ + 34,"Суп по-французски", REST3);
    public static final Meal MEAL_Not_Exist = new Meal(START_SEQ + 50,"Не сушествует", REST1);

    public static final MealTo MEALTO1 = new MealTo("Суп по-французски TO", REST3_id, 25000);

    public static final List<Meal> MEALS = Arrays.asList(MEAL8, MEAL7,MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    public static final List<Meal> MEALS_REST2 = Arrays.asList(MEAL7, MEAL8,MEAL9);

    public static MealTo getCreated() {
        return new MealTo("Суп по-французски TO", REST3_id, 25000);
    }
    public static MealTo getUpdated() {
        return new MealTo(MEAL1.getId(),"Печень по-грузински, с черносивом", REST1_id, 100000);
    }
    public static MealTo getInvalid() { return new MealTo(null,  null, REST1.getId(), 5000);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restouran", "cost").isEqualTo(expected);
    }


    public static void assertMatchTo(MealTo actual, MealTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatchTo(Iterable<MealTo> actual, MealTo... expected) {
        assertMatchTo(actual, Arrays.asList(expected));
    }

    public static void assertMatchTo(Iterable<MealTo> actual, Iterable<MealTo> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restouran").isEqualTo(expected);
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
        MEAL12.setCost(hM13.getCost());
    }
}
