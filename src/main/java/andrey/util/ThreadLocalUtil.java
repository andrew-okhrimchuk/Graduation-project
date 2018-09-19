package andrey.util;

import andrey.model.List_of_admin;
import andrey.model.User;
import andrey.repository.List_of_AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static andrey.model.Role.ROLE_ADMIN;

public class ThreadLocalUtil {

    @Autowired
    private List_of_AdminRepository list_a;

    private ThreadLocal<LocalDateTime> threadLocalScope = new ThreadLocal<>();
    private ThreadLocal<List_of_admin> list_of_admin = new ThreadLocal<>();

    public List_of_admin getList_of_admin() {
        return list_of_admin.get();
    }
    public LocalDateTime getThreadLocalScope() {
        return threadLocalScope.get();
    }

    public void setThreadLocalScope(LocalDateTime dateTime) {
        threadLocalScope.set(dateTime);
    }
    public void setList_of_admin(List_of_admin list) {
        list_of_admin.set(list);
    }

    public void clearDate() {
        threadLocalScope.remove();
    }
    public void clearList() {
        list_of_admin.remove();
    }

    public void Raise_in_ThreadLocal(@NotNull User user){

        if (user.getDateVoitin()!=null) {
            threadLocalScope.set(user.getDateVoitin());
        }else {threadLocalScope.set(null);}


        if (user.getRoles().contains(ROLE_ADMIN)) {
            list_of_admin = new ThreadLocal<>();
            list_of_admin.set(list_a.getByAdminId(user.getId()));
        }

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
