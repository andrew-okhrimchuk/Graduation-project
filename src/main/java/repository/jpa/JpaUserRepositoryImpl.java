package repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.User;
import repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

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
        ThreadLocal<LocalDateTime> threadLocalScope = new ThreadLocal<>();
        List<User> users = em.createNamedQuery(User.BY_EMAIL_2, User.class)
                .setParameter(1, LocalDateTime.now() )
                .setParameter(2, email )
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        User result = DataAccessUtils.singleResult(users);

        if (result.getDateVoitin()!=null) {
            threadLocalScope.set(result.getDateVoitin());
        }else {threadLocalScope.set(null);}

        return result;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
