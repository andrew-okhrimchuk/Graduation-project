package andrey.util;

import andrey.model.List_of_admin;
import andrey.model.User;
import andrey.repository.List_of_AdminRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;

import static andrey.model.Role.ROLE_ADMIN;

public class ThreadLocalUtil {

    @Autowired
    private List_of_AdminRepository list_a;

    private ThreadLocal<LocalDate> threadLocalScope = new ThreadLocal<>();
    private ThreadLocal<List<List_of_admin>> list_of_admin = new ThreadLocal<>();

    public List<List_of_admin> getList_of_admin() {
        return list_of_admin.get();
    }
    public LocalDate getThreadLocalScope() {
        return threadLocalScope.get();
    }

    public void setThreadLocalScope(LocalDate dateTime) {
        threadLocalScope.set(dateTime);
    }
    public void setList_of_admin(List<List_of_admin> list) {
        list_of_admin.set(list);
    }

    public void clearDate() {
        threadLocalScope.remove();
    }
    public void clearList() {
        list_of_admin.remove();
    }

    public void Raise_in_ThreadLocal(@NotNull User user){

        if (user!=null) {
            if (user.getRoles().contains(ROLE_ADMIN)) {
                list_of_admin = new ThreadLocal<>();
                list_of_admin.set(list_a.getByAdminId(user.getId()));
            }

            threadLocalScope.set(user.getDateVoitin());
        }else {threadLocalScope.set(null);}




    }


/*

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
    public static void setTenantId(String tenantId) {
        CONTEXT.set(tenantId);
    }

    public static String getTenantId() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }*/
}
