package repository.jpa;

import model.HistoryMeal;
import model.Meal;
import model.Restouran;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.HistoryMealRepository;
import repository.RestouranRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaHistoryMealRepositoryImpl implements HistoryMealRepository {

    @PersistenceContext
    private EntityManager em;

   @Autowired
    RestouranRepository restouranRepository;


    @Override
    @Transactional
    public HistoryMeal save(HistoryMeal historyMeal, int meal, int restouran, int userId) {
        if (!historyMeal.isNew() && restouranRepository.get(historyMeal.getRestouran().getId(), userId) == null) {
            return null;
        }
        historyMeal.setMeal(em.getReference(Meal.class, meal));
        historyMeal.setRestouran(em.getReference(Restouran.class, restouran));
        if (historyMeal.isNew()) {
            em.persist(historyMeal);
            return historyMeal;
        } else {
            return em.merge(historyMeal);
        }
    }

    @Override
    public HistoryMeal getId(int id){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_MEAL_ID, HistoryMeal.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public boolean delete(int historyMeal_id, int user_id){
        Restouran rest = getId(historyMeal_id).getRestouran();

        if (rest !=null && rest.getUser().getId() == user_id){
        return em.createNamedQuery(HistoryMeal.DELETE)
                .setParameter("id", historyMeal_id)
                .executeUpdate() != 0;
        }
        else return false;

    }

    @Override
    public List<HistoryMeal> getMealId(int meal_id) {
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
