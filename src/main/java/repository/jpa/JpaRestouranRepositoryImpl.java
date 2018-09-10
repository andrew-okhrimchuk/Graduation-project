package repository.jpa;

import model.List_of_admin;
import model.Meal;
import model.Restouran;
import to.MealMenu;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.MealRepository;
import repository.RestouranRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestouranRepositoryImpl implements RestouranRepository {

    @PersistenceContext
    private EntityManager em;
    private final JpaUserRepositoryImpl jpaUserRepositoryImpl;

    @Autowired
    public JpaRestouranRepositoryImpl(JpaUserRepositoryImpl jpaUserRepositoryImpl) {
        this.jpaUserRepositoryImpl = jpaUserRepositoryImpl;
    }

    @Override
    @Transactional
    public Restouran save(Restouran restouran, int userId) {
        List_of_admin list_of_admin = jpaUserRepositoryImpl.getList_of_admin().get();
        if (!restouran.isNew() && list_of_admin != null && list_of_admin.getId() != userId) { //проверка на принадлежность админа к текущему ресторану
            return null;
        }
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
        List_of_admin list_of_admin = jpaUserRepositoryImpl.getList_of_admin().get();
        if (list_of_admin != null && list_of_admin.getId() != userId) { //проверка на принадлежность админа к текущему ресторану
            return false;
        }
        return em.createNamedQuery(Restouran.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Restouran get(int id) {
        return em.find(Restouran.class, id);
    }

    @Override
    public List<Restouran> getAll() {
        return em.createNamedQuery(Restouran.ALL_SORTED, Restouran.class)
                .getResultList();
    }

    @Override
    public List<MealMenu> getManuToday() {
        return em.createNamedQuery(Restouran.MANU, MealMenu.class)
                .setParameter("date", LocalDate.now())
                .getResultList();
    }


}