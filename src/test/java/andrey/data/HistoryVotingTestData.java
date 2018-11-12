package andrey.data;

import andrey.model.HistoryVoting;

import java.time.LocalDate;
import java.util.Arrays;

import static andrey.data.RestouranTestData.*;
import static andrey.data.UserTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
//import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class HistoryVotingTestData {
    public static final int HV_ID = START_SEQ ;

    public static final HistoryVoting hV1 = new HistoryVoting(HV_ID + 18, LocalDate.now(),REST1, ADMIN.getId());
    public static final HistoryVoting hV2 = new HistoryVoting(HV_ID + 19, LocalDate.now(),REST2, USER_3.getId());
    public static final HistoryVoting hV3 = new HistoryVoting(HV_ID + 20, LocalDate.now(),REST3, ADMIN_4.getId());
    public static final HistoryVoting hV4 = new HistoryVoting(HV_ID + 21, LocalDate.now(),REST3, ADMIN_5.getId());
    public static final HistoryVoting hV5 = new HistoryVoting(HV_ID + 22, LocalDate
            .of(2018, 9,21),REST6, ADMIN.getId());

    public static HistoryVoting getCreated() {
        return new HistoryVoting(null,  LocalDate.now(),REST3, ADMIN.getId());
    }
    public static HistoryVoting getUpdated() { return new HistoryVoting(HV_ID + 1, LocalDate.now(),REST1, ADMIN.getId());}

    public static void assertMatch(HistoryVoting actual, HistoryVoting expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<HistoryVoting> actual, HistoryVoting... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public static void assertMatch(Iterable<HistoryVoting> actual, Iterable<HistoryVoting> expected) {
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