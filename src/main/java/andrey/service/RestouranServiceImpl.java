package andrey.service;

import andrey.model.List_of_admin;
import andrey.model.Restouran;
import andrey.repository.List_of_AdminRepository;
import andrey.to.RestouranTo;
import andrey.util.threadLocal.ThreadLocalUtil;
import andrey.util.exception.NotEnoughRightsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import andrey.repository.RestouranRepository;
import andrey.to.MealMenu;

import java.util.List;

import static andrey.model.Role.ROLE_ADMIN;
import static andrey.util.RestouranUtil.asTo;
import static andrey.util.RestouranUtil.updateFromTo;
import static andrey.util.ValidationUtil.checkNotFound;
import static andrey.util.ValidationUtil.checkNotFoundWithId;
import static andrey.util.exception.NotEnoughRightsException.ADMIN_LIST_EMPTY_EXEPTION;

@Service
public class RestouranServiceImpl implements RestouranService {

    private final RestouranRepository restouranRepository;
    private ThreadLocalUtil threadLocalUtil;
    private List_of_AdminRepository list_of_admin;

    private static Expression checkUserRoles = (list,user_id,rest_id)-> list.getUser().getRoles().contains(ROLE_ADMIN);
    private static Expression checkUserIsAdminOfList = (list,user_id,rest_id)-> list.getUser().getId()==user_id;
    private static Expression checkUserIsAdminOfRestouran = (list,user_id,rest_id)-> list.getRestouran().getId()==rest_id;
    private static String messegecheckUserRoles = " User is not admin of restouran ";
    private static String messegecheckUserIsAdminOfRestouran = " User is not admin of restouran ";

    @Autowired
    public RestouranServiceImpl(RestouranRepository repository, ThreadLocalUtil threadLocalUtil, List_of_AdminRepository list ) {
        this.restouranRepository = repository;
        this.threadLocalUtil = threadLocalUtil;
        this.list_of_admin = list;
    }

    @Override
    public Restouran get(int id) {
        return checkNotFoundWithId(restouranRepository.get(id), id);
    }
    @Override
    public List<MealMenu> getManuToday(){
       return restouranRepository.getManuToday();
    }

    @Override
    public List<Restouran> getAll(int userId) {
        return restouranRepository.getAll(userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        get (id);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+ "id rest = "+id,checkUserRoles, id);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +id,checkUserIsAdminOfList, id);
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +id,checkUserIsAdminOfRestouran, id);
        return (restouranRepository.delete(id));
    }

    @Override
    public Restouran update(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");
        get (restouran.getId());
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+restouran.getId(),checkUserRoles, restouran.getId());
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran + "id rest = " +restouran.getId(),checkUserIsAdminOfList, restouran.getId());
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserIsAdminOfRestouran+restouran.getId(),checkUserIsAdminOfRestouran, restouran.getId());
        return restouranRepository.save(restouran, userId);
    }
    @Override
    public RestouranTo update(RestouranTo restouranTo, int userId) {
        Assert.notNull(restouranTo, "RestouranTo must not be null");
        //перевод из RestouranTo  в  Restouran
        Restouran restouran = new Restouran();
        updateFromTo(restouran, restouranTo);

        return asTo(update(restouran, userId));
    }

    @Override
    public Restouran create(Restouran restouran, int userId) {
        Assert.notNull(restouran, "Restouran must not be null");
        if (!restouran.isNew()) {return null;} //этот ресторан должен быть новым!!!
        checkUserIsAdminOfRestouranAndRoles(userId,messegecheckUserRoles+0,checkUserRoles, 0);

        // сохранение в базу
        Restouran create = restouranRepository.save(restouran, userId);

        // добавление записи в базу списка адинов нового ресторана
        List_of_admin newList = new List_of_admin(threadLocalUtil.getList_of_admin().get(0));
        newList.setRestouran(restouran);
        newList.setId(null);
        List_of_admin list_of_admin1 = list_of_admin.save(newList);
        checkNotFound(list_of_admin1, "Не сохранен новый список администраторов, присоздании ресторана");

        // обновление в оперативной памяти список админов созданого ресторана
        List <List_of_admin> xxx = threadLocalUtil.getList_of_admin();
        xxx.add(list_of_admin1);
        threadLocalUtil.setList_of_admin(xxx);

        return create;
    }

    @Override
    public RestouranTo create(RestouranTo restouranTo, int userId) {
        Assert.notNull(restouranTo, "RestouranTo must not be null");
        if (!restouranTo.isNew()) {return null;} //этот ресторан должен быть новым!!!
        //перевод из RestouranTo  в  Restouran
        Restouran restouran = new Restouran();
        updateFromTo(restouran, restouranTo);
        // повторное использование кода :)
        Restouran create = create ( restouran,  userId);
        return asTo(create);
    }




    public  void checkUserIsAdminOfRestouranAndRoles(int userId, String messege, Expression func, int rest_id) {
        // 1.спсик админов не должен быть нулл иначе return null
        // 2.принадлежность админа к текущему ресторану иначе return null
        List<List_of_admin> list_of_admin = threadLocalUtil.getList_of_admin();
        checkNotFound(list_of_admin, ADMIN_LIST_EMPTY_EXEPTION);
        boolean isOk = false;

            for (List_of_admin admin : list_of_admin) {
                if (func.isEqual(admin,userId,rest_id)) {
                    isOk = true;
                }
            }
        if (!isOk) {throw new NotEnoughRightsException("User id = " + userId + messege);
        }
    }

    interface Expression{
        boolean isEqual(List_of_admin list, int user_id, int rest_id);
    }




}
