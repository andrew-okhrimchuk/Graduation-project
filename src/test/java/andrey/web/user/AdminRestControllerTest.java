package andrey.web.user;

import andrey.TestUtil;
import org.junit.Test;
import andrey.json.JsonUtil;
import andrey.model.Role;
import andrey.model.User;
import andrey.util.exception.ErrorType;
import andrey.web.AbstractControllerTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;

import static andrey.TestUtil.userHttpBasic;
import static andrey.data.UserTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static andrey.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;


public class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN_2));
    }

   /* @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }*/

    @Test
    public void testGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + ADMIN_2.getEmail())
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN_2));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN_2, ADMIN_6, ADMIN_4,ADMIN_5,USER_7);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + (START_SEQ+10))
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_3)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(ADMIN_2);
        updated.setName("UpdatedName");
        updated.setRoles(roles);
        mockMvc.perform(put(REST_URL + ADMIN_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(jsonWithPassword(updated, ADMIN_2.getPassword())))
                .andExpect(status().isOk());

        assertMatch(userService.get(ADMIN_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        User expected = USER_8;
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(jsonWithPassword(expected, "newPass")))
                .andExpect(status().isCreated());

        User returned = TestUtil.readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(userService.getAll(), ADMIN_2, ADMIN_6, ADMIN_4,ADMIN_5,expected,USER_3, USER_7 );
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN_2, ADMIN_6, ADMIN_4,ADMIN_5,USER_3, USER_7)));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        User expected = USER_8;
        expected.setName(null);
        expected.setEmail("");
        expected.setPassword(null);

        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        User updated = new User(ADMIN_2);
        updated.setName("");
        mockMvc.perform(put(REST_URL + ADMIN_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }
    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        User updated = new User(USER_3);
        updated.setEmail("admin@ukr.net");
        mockMvc.perform(put(REST_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(jsonWithPassword(updated, "password")))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_EMAIL))
                .andDo(print());
    }
    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        User expected = new User(USER_3);
        expected.setId(null);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(jsonWithPassword(expected, "user-1")))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_EMAIL));

    }


}