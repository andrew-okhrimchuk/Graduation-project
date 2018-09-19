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
@Transactional(readOnly = true)
public class JpaHistoryMealRepositoryImpl implements HistoryMealRepository {
    @PersistenceContext
    private EntityManager em;

    // CRUD
    @Override
    @Transactional
    public HistoryMeal save(HistoryMeal historyMeal, int meal_Id, long cost, LocalDate date, int userId) {

        historyMeal.setMeal(em.getReference(Meal.class, meal_Id));
        historyMeal.setCost(cost);
        historyMeal.setDate(date);
        if (historyMeal.isNew()) {
            em.persist(historyMeal);
            return historyMeal;
        } else {
            return em.merge(historyMeal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int historyMeal_id, int user_id){
        //Restouran rest = getId(historyMeal_id).getRestouran();
        return em.createNamedQuery(HistoryMeal.DELETE)
                .setParameter("id", historyMeal_id)
                .executeUpdate() != 0;
    }

// GET
    @Override
    public HistoryMeal getId(int id){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_MEAL_ID, HistoryMeal.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<HistoryMeal> getByMealId(int meal_id) {
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_MEAL_ID, HistoryMeal.class)
                .setParameter("meal_id", meal_id)
                .getResultList();
    }

    @Override
    public List<HistoryMeal>  getByDateBetween(LocalDate start, LocalDate end){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_DATE_Between, HistoryMeal.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }


    @Override
    public List<HistoryMeal> getRestouranId(int restouran){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_RESTOURAN_ID, HistoryMeal.class)
                .setParameter("restouran", restouran)
                .getResultList();
    }

    @Override
    public List<HistoryMeal> getCost(long cost){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_RESTOURAN_ID, HistoryMeal.class)
                .setParameter("cost", cost)
                .getResultList();
    }

    // ORDERED dateTime desc
    @Override
    public List<HistoryMeal> getAll(){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_MEAL_All, HistoryMeal.class)
                .getResultList();
    }

}
