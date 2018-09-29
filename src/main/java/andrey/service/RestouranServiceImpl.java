package andrey.service;

import andrey.model.List_of_admin;
import andrey.model.Restouran;
import andrey.util.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.repository.RestouranRepository;
import andrey.to.MealMenu;

import java.util.List;

import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestouranServiceImpl implements RestouranService {

    private final RestouranRepository restouranRepository;
    private ThreadLocalUtil threadLocalUtil;

    @Autowired
    public RestouranServiceImpl(RestouranRepository repository, ThreadLocalUtil threadLocalUtil ) {
        this.restouranRepository = repository;
        this.threadLocalUtil = threadLocalUtil;
    }

    @Override
    public Restouran get(int id, int userId) {
        return checkNotFoundWithId(restouranRepository.get(id), userId);
    }
    @Override
    public List<MealMenu> getManuToday(){
       return restouranRepository.getManuToday();
    }

    @Override
    public List<Restouran> getAll() {
        return restouranRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        checkUserIsAdmin(userId);
        return (restouranRepository.delete(id, userId));
    }

    @Override
    public Restouran update(Restouran restouran, int userId) {
        checkUserIsAdmin(userId);
        return checkNotFoundWithId(restouranRepository.save(restouran, userId), restouran.getId());
    }
    @Override
    public Restouran create(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");
        if (!restouran.isNew()) {return null;} //этот ресторан должен быть новым!!!
        checkUserIsAdmin(userId);
        return restouranRepository.save(restouran, userId);
    }

    public  void checkUserIsAdmin(int userId) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        boolean isOk = false;
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        if (list_of_admin == null) {
           throw  new NotEnoughRightsException("User id = " + userId + "not enough rights");
        }
        else {
            for (List_of_admin admin : list_of_admin) {
                if (admin.getId() == userId) {
                    isOk = true;
                }
            }
        }

        if (!isOk) {throw new NotEnoughRightsException("User id = " + userId + "not enough rights");
        }
    }



}
