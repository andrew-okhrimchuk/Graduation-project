package andrey.web.user;

import andrey.TestUtil;
import andrey.json.JsonUtil;
import andrey.model.User;
import andrey.to.UserTo;
import andrey.util.UserUtil;
import andrey.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static andrey.TestUtil.*;
import static andrey.data.UserTestData.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class ProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileRestController.REST_URL + '/';

      @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(ADMIN_2)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(ADMIN_2))
        );
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN_6, ADMIN_4,ADMIN_5,USER_3, USER_7);
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "{noop}newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(ADMIN_2), updatedTo));
    }
/*
    @Test
    public void testUpdateInvalid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, "password", null, 1500);

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testDuplicate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "admin@gmail.com", "newPassword", 1500);

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_EMAIL))
                .andDo(print());
    }*/
}