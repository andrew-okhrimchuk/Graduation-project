package andrey.repository.jpa;

import andrey.model.HistoryVoting;
import andrey.model.Restouran;
import andrey.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import andrey.repository.HistoryVotingRepository;
import andrey.repository.RestouranRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaHistoryVotingRepositoryImpl implements HistoryVotingRepository {

    @PersistenceContext
    private EntityManager em;
 /*@Autowired
    private ThreadLocal tl;
*/

    @Override
    @Transactional
    public HistoryVoting save(HistoryVoting historyVoting, int restouran, int userId) {
/* User user = (User)tl.get();
        if (user.isVoting()) {
            return null;
        }*/

        /*if (!historyVoting.isNew() && getVotingTodayByUser(userId) == null) {
            return null;
        }*/
       // historyVoting.setUser(em.getReference(User.class, userId));
        historyVoting.setUser( userId);

        historyVoting.setRestouran(em.getReference(Restouran.class, restouran));
        if (historyVoting.isNew()) {
            em.persist(historyVoting);
            return historyVoting;
        } else {
            return em.merge(historyVoting);
        }
    }


    @Override
    public HistoryVoting getVotingTodayByUser(int userId) {
        return em.createNamedQuery(HistoryVoting.GET_VOTING_BY_DATE_BY_USER_ID, HistoryVoting.class)
                .setParameter("userId", userId)
                .setParameter("dateTime", LocalDate.now())
                .getSingleResult();
    }


    @Override
    public List<HistoryVoting>  getByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime){
        return em.createNamedQuery(HistoryVoting.GET_VOTING_BY_DATE_Between, HistoryVoting.class)
                .setParameter("start", startDateTime)
                .setParameter("end", endDateTime)
                .getResultList();
    }

    @Override
    public List<HistoryVoting> getByRestouranId(int restouran){
        return em.createNamedQuery(HistoryVoting.GET_VOTING_BY_RESTOURAN_ID, HistoryVoting.class)
                .setParameter("restouran", restouran)
                .getResultList();
    }

    @Override
    public List<HistoryVoting> getByUser(int userId){
        return em.createNamedQuery(HistoryVoting.GET_VOTING_BY_USER_ID, HistoryVoting.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    // ORDERED dateTime desc
    @Override
    public List<HistoryVoting> getAll(){
        return em.createNamedQuery(HistoryVoting.GET_All, HistoryVoting.class)
                .getResultList();
    }

}
