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
    public Meal save(Meal meal) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
      //  meal.setRestouran(em.getReference(Restouran.class, restouranId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
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
    public Meal get(int id) {
        return em.find(Meal.class, id);
    }

    @Override
    public List<Meal> getAll(int restouranId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("restouranId", restouranId)
                .getResultList();
    }


}