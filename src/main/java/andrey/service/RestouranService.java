package andrey.service;


import andrey.model.Restouran;
import andrey.to.MealMenu;
import andrey.to.RestouranTo;
import andrey.util.exception.NotFoundException;

import java.util.List;

public interface RestouranService {

    Restouran get(int id) throws NotFoundException;

    List<MealMenu> getManuToday();

    boolean delete(int id, int userId) throws NotFoundException;

    List<Restouran> getAll(int userId);

    Restouran update(Restouran restouran, int userId) throws NotFoundException;

    RestouranTo update(RestouranTo restouran, int userId) throws NotFoundException;

    Restouran create(Restouran restouran, int userId);

    RestouranTo create(RestouranTo restouran, int userId);
}