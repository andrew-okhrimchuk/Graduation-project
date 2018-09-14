package andrey.repository.jpa;

import lombok.Getter;
import lombok.Setter;
import andrey.model.List_of_admin;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.model.User;
import andrey.repository.List_of_AdminRepository;
import andrey.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static andrey.model.Role.ROLE_ADMIN;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;
    final List_of_AdminRepository list;

    @Autowired
    public JpaUserRepositoryImpl(List_of_AdminRepository list) {
        this.list = list;
    }


    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {



        List<User> users = em.createNamedQuery(User.BY_EMAIL_2, User.class)
                .setParameter(1, LocalDateTime.now() )
                .setParameter(2, email )
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        User result = DataAccessUtils.singleResult(users);
        Raise_in_ThreadLocal(result);
        return result;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }


    @Getter @Setter
    private ThreadLocal<LocalDateTime> threadLocalScope = new ThreadLocal<>();
    @Getter @Setter
    private ThreadLocal<List_of_admin> list_of_admin;
    private void Raise_in_ThreadLocal(@NotNull User user){

        if (user.getDateVoitin()!=null) {
            threadLocalScope.set(user.getDateVoitin());
        }else {threadLocalScope.set(null);}


        if (user.getRoles().contains(ROLE_ADMIN)) {
            list_of_admin = new ThreadLocal<>();
            list_of_admin.set(list.getByAdminId(user.getId()));
        }

    }
}
