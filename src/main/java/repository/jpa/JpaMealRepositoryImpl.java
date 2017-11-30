package repository.jpa;

import model.Restouran;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Meal;
import repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)

public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int restouranId) {
        if (meal.isNew()) {
            Restouran ref = em.getReference(Restouran.class, restouranId);
            meal.setRestouran(ref);
            em.persist(meal);
            return meal;
        } else {
            if (meal.getRestouran() == null) {return null;}
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        return em.createNamedQuery(Meal.DELETE_All)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id) {
        List<Meal> meals = em.createNamedQuery(Meal.BY_FIND, Meal.class)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll() {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).getResultList();
    }

    @Override
    public List<Meal> getAllMealToday() {
        return em.createNamedQuery(Meal.getMealToday, Meal.class).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return em.createNamedQuery(Meal.getBetween, Meal.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }


    @Override
    public List<Meal> getAllMealOfRestouran(int restouranId) {
        return em.createNamedQuery(Meal.ALL_SORTED_RESTOURAN, Meal.class).setParameter("restouranId", restouranId).getResultList();
    }



}