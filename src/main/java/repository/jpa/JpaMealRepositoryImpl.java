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
    public Meal get(int id) {
        List<Meal> meals = em.createNamedQuery(Meal.BY_FIND, Meal.class)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }
    @Override
    public List<Meal> getBetweenAllMealOfRestouran(LocalDateTime startDate, LocalDateTime endDate, int restouranId){
        LocalDateTime startDate1 = startDate;
        LocalDateTime endDate2 = endDate;
        if (startDate == null) {startDate1 = LocalDateTime.MIN;}
        if (endDate == null) {endDate2 = LocalDateTime.MAX;}

        if (restouranId == 0) {
            return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                    .setParameter("startDate", startDate1)
                    .setParameter("endDate", endDate2)
                    .getResultList();
        }

        return em.createNamedQuery(Meal.ALL_SORTED_RESTOURAN, Meal.class)
                .setParameter("startDate", startDate1)
                .setParameter("endDate", endDate2)
                .setParameter("restouranId", restouranId)
                .getResultList();
    }
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


}