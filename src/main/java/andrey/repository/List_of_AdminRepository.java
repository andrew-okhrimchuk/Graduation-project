package andrey.repository;


import andrey.model.List_of_admin;

import java.util.List;

public interface List_of_AdminRepository {
    List_of_admin save(List_of_admin item);

    // false if not found
    boolean delete(int id);

    // null if not found
    List_of_admin getId(int id);

    // null if not found
    List_of_admin getByResrouranId(int id);

    // null if not found
    List<List_of_admin> getByAdminId(int id);

    List<List_of_admin> getAll();

    default List_of_admin getWithMeals(int id){
        throw new UnsupportedOperationException();
    }
}