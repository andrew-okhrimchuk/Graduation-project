package andrey.web.HV;

import andrey.json.JsonUtil;
import andrey.model.Meal;
import andrey.service.MealService;
import andrey.to.MealTo;
import andrey.util.exception.ErrorType;
import andrey.web.AbstractControllerTest;
import andrey.web.historyVoting.HistoryVotingRestController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static andrey.TestUtil.*;
import static andrey.data.HistoryVotingTestData.*;
import static andrey.data.MealToTestData.*;
import static andrey.data.RestouranTestData.REST1;
import static andrey.data.RestouranTestData.REST1_id;
import static andrey.data.UserTestData.ADMIN;
import static andrey.data.UserTestData.USER_3;
import static andrey.model.AbstractBaseEntity.START_SEQ;
import static andrey.util.MealsUtil.asTo;
import static andrey.web.historyVoting.HistoryVotingRestController.*;
import static andrey.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_NAME_MEAL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HVRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = HistoryVotingRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    public void getToday() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_3)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray( hV4, hV3, hV2, hV1));
    }
    @Test
    public void getByDateToday() throws Exception {
        mockMvc.perform(get(REST_URL + LocalDate.now())
                .with(userHttpBasic(USER_3)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray( hV4, hV3, hV2, hV1));
    }

    @Test
    public void createVoting() throws Exception {
        mockMvc.perform(post(REST_URL + REST1_id)
                .with(userHttpBasic(USER_3)))
                .andExpect(status().isOk())
                .andDo(print())
                .equals(hV4);
    }

   /* @Test
    public void testGetWithDate() throws Exception {
        Meal mealWithCost = new Meal(MEAL1_ID,  "Печень по-грузински", REST1);
        mealWithCost.setCost(1800);

        mockMvc.perform(get(REST_URL + MEAL1_ID +"/"+ LocalDate.now())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(mealWithCost));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + (START_SEQ - 1) +"?date=")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID)
               .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(REST1_id), MEAL2,MEAL3,MEAL4,MEAL5 );
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + (MEAL1_ID-1))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void testUpdate() throws Exception {
        MealTo updated = getUpdated();
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

     //   assertMatch(service.get(MEAL1_ID, null), updated);
        assertMatchTo(asTo(service.get(updated.getId(), LocalDate.now())), updated);

    }
    @Test
    public void testCreate() throws Exception {
        Meal created = new Meal(null,  "Мамалыга", REST1,555l );
        MealTo createdTo = asTo(created);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(createdTo)));
        MealTo returnedTo = readFromJson(action, MealTo.class);
        createdTo.setId(returnedTo.getId());

        assertMatchTo(returnedTo, createdTo);
        assertMatchTo(asTo(service.get(returnedTo.getId(), LocalDate.now())), createdTo);
    }

    @Test
    public void testGetAll() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "restouran/" + REST1_id )
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        MealTo invalid = getInvalid();
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
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
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        Meal created = new Meal(null,  "Печень по-грузински", REST1,555l );
        MealTo invalid = asTo(created);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_NAME_MEAL));
    }*/
}