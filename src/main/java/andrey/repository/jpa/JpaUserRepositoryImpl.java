package andrey.repository.jpa;

import andrey.to.UserTo;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static andrey.model.Role.ROLE_ADMIN;

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

  //  @Override
    public User getByEmail(String email) {
        List<UserTo> users = em.createNamedQuery(User.BY_EMAIL_3, UserTo.class)
             //   .setParameter("date", LocalDate.now())
         //       .setParameter("id", 100006)
              //  .setParameter("dateTimeEnd", LocalDateTime.of(LocalDate.now(), LocalTime.MAX) )
                .setParameter("email", email )
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
        UserTo userTo = DataAccessUtils.singleResult(users);

        if (userTo!=null){
            userTo.init();
            return userTo.getUser();
        }

        return null;
    }


    /*
      SELECT DISTINCT  NEW andrey.to.UserTo(u, b)  " +
                 "FROM User u  LEFT JOIN HistoryVoting b ON b.user.id = u.id LEFT JOIN u.email WHERE u.email =:email " +
                 "WHERE  u.email=:email AND b.dateTime = :date OR b.dateTime IS NULL  */
    //@Override
    public User getByEmail2(String email) {
        List<Object[]> users = em.createNamedQuery(User.BY_EMAIL_3)
             //   .setParameter("date", LocalDate.now())
               // .setParameter("date_0", LocalDate.of(0000, 1,1))
              //  .setParameter("id", 100005)
                .setParameter("email", email )
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
       // UserTo userTo = DataAccessUtils.singleResult(users);

     /*   if (userTo!=null){
            userTo.init();
            return userTo.getUser();
        }
*/
        return null;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
