package andrey.web.meal;

import andrey.json.JsonUtil;
import andrey.model.Meal;
import andrey.service.MealService;
import andrey.util.exception.ErrorType;
import andrey.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static andrey.TestUtil.*;
import static andrey.data.MealTestData.*;
import static andrey.data.RestouranTestData.REST1;
import static andrey.data.RestouranTestData.REST1_id;
import static andrey.data.UserTestData.*;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static andrey.web.ExceptionInfoHandler.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID)
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + (START_SEQ - 1))
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID)
               .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(REST1_id), MEAL3,MEAL2,MEAL4,MEAL5 );
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + (MEAL1_ID-1))
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();

        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk());

        assertMatch(service.get(MEAL1_ID), updated);
    }
    @Test
    public void testCreate() throws Exception {
        Meal created = getCreated();
        created.setRestouran(REST1);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_2))
                .content(JsonUtil.writeValue(created)));

        Meal returned = readFromJson(action, Meal.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(REST1_id), MEAL1,MEAL3,MEAL2,created,MEAL4,MEAL5 );
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "?id=" + REST1_id )
                .with(userHttpBasic(ADMIN_2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Meal invalid = getInvalid();
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Meal invalid = new Meal(MEAL1_ID, null, null);
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        Meal invalid = new Meal(null,  "Печень по-грузински", REST1);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN_2)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_NAME_MEAL));
    }
}