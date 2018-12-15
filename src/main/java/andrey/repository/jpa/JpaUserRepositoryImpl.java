package andrey.repository.jpa;

import andrey.to.UserToDb;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.model.User;
import andrey.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        List<UserToDb> users = em.createNamedQuery(User.BY_EMAIL_3, UserToDb.class)
                .setParameter("email", email )
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
        UserToDb userTo = DataAccessUtils.singleResult(users);

        if (userTo!=null){
            userTo.init();
            return userTo.getUser();
        }

        return null;
    }


    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
