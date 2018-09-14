package andrey.repository.jpa;
import andrey.model.Restouran;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.model.Meal;
import andrey.repository.MealRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int restouranId) {
        if (!meal.isNew() && get(meal.getId(), restouranId) == null) {
            return null;
        }
        meal.setRestouran(em.getReference(Restouran.class, restouranId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restouranId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("restouranId", restouranId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int restouranId) {
        Meal meal = em.find(Meal.class, id);
        return meal != null && meal.getRestouran().getId() == restouranId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int restouranId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("restouranId", restouranId)
                .getResultList();
    }


}