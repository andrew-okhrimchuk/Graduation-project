package andrey.service;

import andrey.model.List_of_admin;
import andrey.repository.List_of_AdminRepository;
import andrey.util.threadLocal.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static andrey.util.ValidationUtil.*;

@Repository
@Transactional
public class List_of_adminServiceImpl implements List_of_AdminServise {

    private final List_of_AdminRepository repository;
    final private ThreadLocalUtil threadLocalUtil;

    @Autowired
    public List_of_adminServiceImpl(List_of_AdminRepository repository, ThreadLocalUtil threadLocalUtil) {
        this.repository = repository;
        this.threadLocalUtil = threadLocalUtil;
    }

    @Override
    public List_of_admin save(List_of_admin item) {
        Assert.notNull(item, "HistoryMeal(u) must not be null");
        return checkNotFoundWithId(repository.save(item), item.getId());
}

    @Override
    public List_of_admin getId(int id) {
    return  checkNotFoundWithId(repository.getId(id), id);
    }

    @Override
    public void delete(int id) {
         checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<List_of_admin> getByResrouranId (int restouran_id) {
        return checkNotFoundListWithId(repository.getByResrouranId(restouran_id), restouran_id);
    }

    public List<List_of_admin>  getByAdminId(int admin_id){
        return checkNotFoundListWithId(repository.getByAdminId(admin_id), admin_id);
    }

    @Override
    public List<List_of_admin> getAll() {
        return repository.getAll();
    }
}
