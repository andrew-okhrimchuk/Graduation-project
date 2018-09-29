package andrey.service;


import andrey.model.Restouran;
import andrey.to.MealMenu;
import andrey.util.exception.NotFoundException;

import java.util.List;

public interface RestouranService {

    Restouran get(int id, int userId) throws NotFoundException;

    List<MealMenu> getManuToday();

    boolean delete(int id, int userId) throws NotFoundException;

    List<Restouran> getAll();

    Restouran update(Restouran restouran, int userId) throws NotFoundException;

    Restouran create(Restouran restouran, int userId);
}