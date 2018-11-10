package andrey.repository.jpa;
import andrey.model.Restouran;
import andrey.to.MealTo;
import andrey.to.MealToDb;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.model.Meal;
import andrey.repository.MealRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static andrey.util.MealsUtil.*;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(MealTo mealTo) {
        if (!mealTo.isNew() && getWithLastCost(mealTo.getId()) == null) {
            return null;
        }
        Meal meal = new Meal();
        if (mealTo.isNew()){meal = createNewFromTo(mealTo);}
        else {meal = updateFromTo(meal, mealTo);}

        meal.setRestouran(em.getReference(Restouran.class, mealTo.getRestouran_id()));
        if (meal.isNew()) {
            em.persist(meal);
            return  meal;
        } else {
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
    public Meal getWithLastCost(int id) {
        List<MealToDb> mealTo = em.createNamedQuery(Meal.BY_FINDLastDATE, MealToDb.class)
                .setParameter("id", id)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
        MealToDb meal = DataAccessUtils.singleResult(mealTo);

        if (meal!=null){
            meal.init();
            return meal.getMeal();
        }
        return null;
    }

    @Override
    public Meal getWithDate(int id, LocalDate date) {
        List<MealToDb> mealTo = em.createNamedQuery(Meal.BY_FINDDATE, MealToDb.class)
                .setParameter("date", date)
                .setParameter("id", id)
                .getResultList();
        MealToDb meal = DataAccessUtils.singleResult(mealTo);

        if (meal!=null){
            meal.init();
            return meal.getMeal();
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int restouranId) {
        List<MealToDb> mealTo = em.createNamedQuery(Meal.ALL_SORTED, MealToDb.class)
                .setParameter("restouranId", restouranId)
                .getResultList();

        if (mealTo!=null){
            List<Meal> result = new ArrayList<>();

            for(MealToDb x : mealTo) {
            x.init();
                result.add(x.getMeal());
            }
            return result;
        }
        return null;

    }


}