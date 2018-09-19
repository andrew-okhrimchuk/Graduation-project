package andrey.service;

import andrey.model.List_of_admin;
import andrey.model.Restouran;
import andrey.util.ThreadLocalUtil;
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
    public List<Restouran> getAll(int userId) {
        return restouranRepository.getAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        List_of_admin list_of_admin = threadLocalUtil.getList_of_admin();
        if (list_of_admin != null && list_of_admin.getId() != userId) { //проверка на принадлежность админа к текущему ресторану
            return false;
        }
        return (restouranRepository.delete(id, userId));
    }

    @Override
    public Restouran update(Restouran restouran, int userId) {

        List_of_admin list_of_admin = threadLocalUtil.getList_of_admin();
        if (!restouran.isNew() && list_of_admin != null && list_of_admin.getId() != userId) { //проверка на принадлежность админа к текущему ресторану
            return null;
        }
        return checkNotFoundWithId(restouranRepository.save(restouran, userId), restouran.getId());
    }
    @Override
    public Restouran create(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");

        List_of_admin list_of_admin = threadLocalUtil.getList_of_admin();
        if (!restouran.isNew() && list_of_admin != null && list_of_admin.getId() != userId) { //проверка на принадлежность админа к текущему ресторану
            return null;
        }

        return restouranRepository.save(restouran, userId);
    }



}
