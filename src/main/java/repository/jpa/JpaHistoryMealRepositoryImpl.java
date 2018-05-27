package repository.jpa;

import model.HistoryMeal;
import model.Meal;
import model.Restouran;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.HistoryMealRepository;
import repository.MealRepository;
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
 @PersistenceContext
    private ThreadLocal tl;

   @Autowired
    RestouranRepository restouranRepository;


    @Override
    @Transactional
    public HistoryMeal save(HistoryMeal historyMeal, int meal, int restouran, int userId) {
 User user = (User)tl.get();
        if (user.isVoting()) {
            return null;
        }

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
    public List<HistoryMeal> getMealId(int id) {
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_MEAL_ID, HistoryMeal.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<HistoryMeal>  getDate(LocalDate date){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_DATE, HistoryMeal.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<HistoryMeal> getRestouranId(int restouran){
        return em.createNamedQuery(HistoryMeal.GET_HISTORY_BY_RESTOURAN_ID, HistoryMeal.class)
                .setParameter("restouran", restouran)
                .getResultList();
    }

    @Override
    public List<HistoryMeal> getCost(int cost){
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
