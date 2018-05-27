package repository.jpa;

import model.Meal;
import model.Restouran;
import model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.MealRepository;
import repository.RestouranRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestouranRepositoryImpl implements RestouranRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restouran save(Restouran restouran, int userId) {
        if (!restouran.isNew() && get(restouran.getId(), userId) == null) {
            return null;
        }
        restouran.setUser(em.getReference(User.class, userId));
        if (restouran.isNew()) {
            em.persist(restouran);
            return restouran;
        } else {
            return em.merge(restouran);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Restouran get(int id, int userId) {
        Restouran restouran = em.find(Restouran.class, id);
        return restouran != null && restouran.getUser().getId() == userId ? restouran : null;
    }

    @Override
    public List<Restouran> getAll(int userId) {
        return em.createNamedQuery(Restouran.ALL_SORTED, Restouran.class)
                .setParameter("userId", userId)
                .getResultList();
    }


}