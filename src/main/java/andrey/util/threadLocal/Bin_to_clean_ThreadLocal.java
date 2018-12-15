package andrey.util.threadLocal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

@Component()
@Scope(value = "request")
@Setter @Getter
public class Bin_to_clean_ThreadLocal {
    private ThreadLocalUtil threadLocalUtil;

    @PreDestroy
    private void clean_ThreadLocal(){
        threadLocalUtil.clearDate();
        threadLocalUtil.clearList();
        threadLocalUtil = null;
    }
}
