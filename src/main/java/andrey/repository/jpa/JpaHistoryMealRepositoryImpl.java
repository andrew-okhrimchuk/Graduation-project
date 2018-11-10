package andrey.repository.jpa;

import andrey.model.HistoryMeal;
import andrey.model.Meal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.repository.HistoryMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional()
public class JpaHistoryMealRepositoryImpl implements HistoryMealRepository {
    @PersistenceContext
    private EntityManager em;

    // CRUD
    @Override
    @Transactional
    public HistoryMeal save(HistoryMeal historyMeal, Meal meal, long cost ) {

        historyMeal.setMeal(meal);
        historyMeal.setCost(cost);
        historyMeal.setDate(LocalDate.now());
        if (historyMeal.isNew()) {
            em.persist(historyMeal);
            return historyMeal;
        } else {
            return em.merge(historyMeal);
        }
    }

// GET
    @Override
    public HistoryMeal getMealId(int id){
        List <HistoryMeal> results  = em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_MEAL_IDDate, HistoryMeal.class)
                .setParameter("id", id)
                .setParameter("date", LocalDate.now())
                .getResultList();
        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;

    }
    @Override
    public List<HistoryMeal> getAll(){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_MEAL_All, HistoryMeal.class)
                .getResultList();
    }


}
