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

import static andrey.model.Role.ROLE_ADMIN;
import static andrey.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestouranServiceImpl implements RestouranService {

    private final RestouranRepository restouranRepository;
    private ThreadLocalUtil threadLocalUtil;
    private static Expression checkUserRoles = (list,user_id,rest_id)-> list.getUser().getRoles().contains(ROLE_ADMIN);
    private static Expression checkUserIsAdminOfList = (list,user_id,rest_id)-> list.getUser().getId()==user_id;
    private static Expression checkUserIsAdminOfRestouran = (list,user_id,rest_id)-> list.getRestouran().getId()==rest_id;
    private static String messegecheckUserRoles = " User is not admin of restouran ";
    private static String messegecheckUserIsAdminOfRestouran = " User is not admin of restouran ";

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
        get (id, userId);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+ "id rest = "+id,checkUserRoles, id);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +id,checkUserIsAdminOfList, id);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +id,checkUserIsAdminOfRestouran, id);
        return (restouranRepository.delete(id, userId));
    }

    @Override
    public Restouran update(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");
        get (restouran.getId(), userId);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+restouran.getId(),checkUserRoles, restouran.getId());
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +restouran.getId(),checkUserIsAdminOfList, restouran.getId());
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran+restouran.getId(),checkUserIsAdminOfRestouran, restouran.getId());
        return restouranRepository.save(restouran, userId);
    }
    @Override
    public Restouran create(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");
        if (!restouran.isNew()) {return null;} //этот ресторан должен быть новым!!!
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+0,checkUserRoles, 0);
        return restouranRepository.save(restouran, userId);
    }



    public  void checkUserIsAdminOfRestouranAndRoles(int userId, String messege, Expression func, int rest_id) {
        //проверка на:
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        boolean isOk = false;
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();

        if (list_of_admin == null) {
           throw  new NotEnoughRightsException("User id = " + userId + messege + ". User is not admin!");
        }
        else {
            for (List_of_admin admin : list_of_admin) {
                if (func.isEqual(admin,userId,rest_id)) {
                    isOk = true;
                }
            }
        }

        if (!isOk) {throw new NotEnoughRightsException("User id = " + userId + messege);
        }
    }

    interface Expression{
        boolean isEqual(List_of_admin list, int user_id, int rest_id);
    }


}
