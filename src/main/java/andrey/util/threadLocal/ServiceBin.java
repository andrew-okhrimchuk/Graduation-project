package andrey.util.threadLocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ServiceBin {
    private ThreadLocalUtil threadLocalUtil;

    @Autowired
    public ServiceBin(ThreadLocalUtil threadLocalUtil) {
        this.threadLocalUtil = threadLocalUtil;
    }
    public void set_ThreadLoca_to_Bin(){
        Bin_to_clean_ThreadLocal bean = getBin();
        bean.setThreadLocalUtil(threadLocalUtil);
    }
    @Lookup
    public Bin_to_clean_ThreadLocal getBin() {
        return null;
    }
    public ServiceBin() {
    }
}
