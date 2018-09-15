package andrey.data;

import andrey.model.Meal;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static andrey.data.RestouranTestData.*;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static andrey.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 2;

    public static final Meal MEAL1 = new Meal(MEAL1_ID,  "Печень по-грузински", REST1);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1,  "Пахлава", REST1);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2,  "Ужин", REST2);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, "автрак", REST2);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4,"ед", REST2);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5,  "Ужин", REST2);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6,  "Админ ланч", REST2);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7,  "Админ ужин", REST2);

    public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getCreated() {
        return new Meal(null,  "Созданный ужин", REST2);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID,  "Обновленный завтрак", REST2);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}
