package repository.jpa;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.HistoryMealRepository;
import repository.MealRepository;
import repository.List_of_AdminRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaHistoryMealRepositoryImpl implements HistoryMealRepository {
    @PersistenceContext
    private EntityManager em;

   final private MealRepository mealRepository;
   final private JpaUserRepositoryImpl jpaUserRepositoryImpl;

    @Autowired
    public JpaHistoryMealRepositoryImpl(MealRepository mealRepository, JpaUserRepositoryImpl jpaUserRepositoryImpl) {
        this.mealRepository = mealRepository;
        this.jpaUserRepositoryImpl = jpaUserRepositoryImpl;
    }

    // CRUD
    @Override
    @Transactional
    public HistoryMeal save(HistoryMeal historyMeal, int meal_Id, long cost, LocalDate date, int userId) {
        List_of_admin admin = jpaUserRepositoryImpl.getList_of_admin().get();

        if (!historyMeal.isNew()&& admin !=null && date!=LocalDate.now() && mealRepository.get(meal_Id, admin.getRestouran().getId()) == null) {
            return null;
        }
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
        HistoryMeal check_date = getId(historyMeal_id);
        if (check_date.getDate() != LocalDate.now()){
            return false;}

        List_of_admin admin = jpaUserRepositoryImpl.getList_of_admin().get(); //проверка - принадлежит ли админ ресторану
        Meal chekMeal = mealRepository.get(check_date.getMeal().getId() , admin.getRestouran().getId()); // проверка - принадлежит ли еда ресторану

        if (admin.getRestouran().getId() == user_id && chekMeal != null){
        return em.createNamedQuery(HistoryMeal.DELETE)
                .setParameter("id", historyMeal_id)
                .executeUpdate() != 0;
        }
        else return false;

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
