package andrey.web.restouran;

import andrey.AuthorizedUser;
import andrey.model.Restouran;
import andrey.service.RestouranService;
import andrey.to.RestouranTo;
import andrey.util.threadLocal.ServiceBin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static andrey.util.ValidationUtil.assureIdConsistent;
import static andrey.util.ValidationUtil.checkNew;


public abstract class AbstractRestouranController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private  RestouranService service;
    @Autowired
    private  ServiceBin serviceBin;

    public Restouran get(int id) {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        log.info("get Restouran {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restouran> getAll() {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public RestouranTo create(RestouranTo restouranTo) {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        checkNew(restouranTo);
        log.info("create {} for user {}", restouranTo, userId);
        return service.create(restouranTo, userId);
    }
    public void update(RestouranTo restouranTo, int id) {
        serviceBin.set_ThreadLoca_to_Bin();
        int userId = AuthorizedUser.id();
        assureIdConsistent(restouranTo, id);
        log.info("update {} for user {}", restouranTo, userId);
        service.update(restouranTo, userId);
    }
}