package andrey.data;

import andrey.model.HistoryVoting;

import java.time.LocalDateTime;
import java.util.Arrays;

import static andrey.data.MealTestData.*;
import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class HistoryVotingTestData {

    public static final HistoryVoting hV1 = new HistoryVoting(1, LocalDateTime.now(),REST1, USER);
    public static final HistoryVoting hV2 = new HistoryVoting(2, LocalDateTime.now(),REST2, ADMIN);
    public static final HistoryVoting hV5 = new HistoryVoting(3, LocalDateTime
            .of(2018, 9,21, 20,30, 40),REST3, USER);


    public static void assertMatch(HistoryVoting actual, HistoryVoting expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

    public static void assertMatch(Iterable<HistoryVoting> actual, HistoryVoting... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<HistoryVoting> actual, Iterable<HistoryVoting> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
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