package andrey.repository.jpa;

import andrey.model.Restouran;
import andrey.to.MealMenu;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.repository.RestouranRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestouranRepositoryImpl implements RestouranRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restouran save(Restouran restouran, int userId) {
        if (restouran.isNew()) {
            em.persist(restouran);
            return restouran;
        } else {
            return em.merge(restouran);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restouran.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Restouran get(int id) {
        return em.find(Restouran.class, id);
    }

    @Override
    public List<Restouran> getAll(int userId) {
        return em.createNamedQuery(Restouran.ALL_SORTED, Restouran.class)
                .setParameter("id", userId)
                .getResultList();
    }

    @Override
    public List<MealMenu> getManuToday() {
        return em.createNamedQuery(Restouran.MANU, MealMenu.class)
                .setParameter("date", LocalDate.now())
                .getResultList();
    }


}