package andrey.web.user;

import andrey.model.AbstractBaseEntity;
import andrey.model.User;
import andrey.service.UserService;
import andrey.to.UserTo;
import andrey.util.exception.ModificationRestrictionException;
import andrey.util.threadLocal.ServiceBin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


import java.util.List;

import static andrey.util.ValidationUtil.assureIdConsistent;
import static andrey.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private  UserService service;
    @Autowired
    private  ServiceBin serviceBin;
    private boolean modificationRestriction = true;

    @Autowired
    public void setEnvironment(Environment environment) {
       // modificationRestriction = environment.acceptsProfiles(Profiles.HEROKU);
    }

    public List<User> getAll() {
        log.info("getAll");
        serviceBin.set_ThreadLoca_to_Bin();
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        serviceBin.set_ThreadLoca_to_Bin();
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        serviceBin.set_ThreadLoca_to_Bin();
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        serviceBin.set_ThreadLoca_to_Bin();
        checkModificationAllowed(id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        serviceBin.set_ThreadLoca_to_Bin();
        assureIdConsistent(user, id);
        checkModificationAllowed(id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        serviceBin.set_ThreadLoca_to_Bin();
        assureIdConsistent(userTo, id);
        checkModificationAllowed(id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        serviceBin.set_ThreadLoca_to_Bin();
        return service.getByEmail(email);
    }



    private void checkModificationAllowed(int id) {
        if (modificationRestriction && id < AbstractBaseEntity.START_SEQ) {
            throw new ModificationRestrictionException();
        }
    }

    public String testUTF() {
        serviceBin.set_ThreadLoca_to_Bin();
        return serviceBin.getBin().getThreadLocalUtil().getThread_HV().toString();
    }

}