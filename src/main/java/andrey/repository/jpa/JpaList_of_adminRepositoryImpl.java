package andrey.repository.jpa;

import andrey.model.List_of_admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.repository.List_of_AdminRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaList_of_adminRepositoryImpl implements List_of_AdminRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List_of_admin save(List_of_admin item) {
        if (item.isNew()) {
            em.persist(item);
            return item;
        } else {
            return em.merge(item);
        }
    }

    @Override
    public List_of_admin getId(int id) {
        return em.find(List_of_admin.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        return em.createNamedQuery(List_of_admin.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<List_of_admin> getByResrouranId (int restouran_id) {
        List<List_of_admin> users = em.createNamedQuery(List_of_admin.BY_RESTOURAN_ID, List_of_admin.class)
                .setParameter("restouran_id", restouran_id)
                .getResultList();
        return users;

    }

    public List<List_of_admin>  getByAdminId(int admin_id){
        List<List_of_admin> users = em.createNamedQuery(List_of_admin.BY_USER_ID, List_of_admin.class)
                .setParameter("admin_id", admin_id)
                .getResultList();
        return users;

    }

    @Override
    public List<List_of_admin> getAll() {
        return em.createNamedQuery(List_of_admin.ALL_SORTED, List_of_admin.class).getResultList();
    }
}
