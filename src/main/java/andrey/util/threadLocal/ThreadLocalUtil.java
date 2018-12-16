package andrey.util.threadLocal;

import andrey.model.HistoryVoting;
import andrey.model.List_of_admin;
import andrey.model.User;
import andrey.repository.List_of_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static andrey.model.Role.ROLE_ADMIN;

public class ThreadLocalUtil {

    @Autowired
    private List_of_AdminRepository list_a;

    private ThreadLocal<HistoryVoting> thread_HV = new ThreadLocal<>();
    private ThreadLocal<List<List_of_admin>> list_of_admin = new ThreadLocal<>();

    public List<List_of_admin> getList_of_admin() {
        return list_of_admin.get();
    }
    public HistoryVoting getThread_HV() {
        return thread_HV.get();
    }

    public void setThread_HV(HistoryVoting threadHV) {
        thread_HV.set(threadHV);
    }
    public void setList_of_admin(List<List_of_admin> list) {
        list_of_admin.set(list);
    }

    public void clearDate() {
        thread_HV.remove();
    }
    public void clearList() {
        list_of_admin.remove();
    }

    public void Raise_in_ThreadLocal(User user){

        if (user!=null) {
            if (user.getRoles().contains(ROLE_ADMIN)) {
                list_of_admin = new ThreadLocal<>();
                list_of_admin.set(list_a.getByAdminId(user.getId()));
            }
            if (user.getHistoryVoting() != null) {
                thread_HV.set(user.getHistoryVoting());
            }
            else if (user.getHistoryVoting() == null) {thread_HV.set(null);}// do it only for test
        }
    }
}
