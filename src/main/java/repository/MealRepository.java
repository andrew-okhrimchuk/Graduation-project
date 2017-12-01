package repository;
import model.Meal;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal get(int id);

/*
    List<Meal> getAll();
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate);
*/
    List<Meal> getBetweenAllMealOfRestouran(LocalDateTime startDate, LocalDateTime endDate, int restouranId);

    Meal save(Meal meal, int restouranId);
    boolean delete(int id);
    boolean deleteAll();
}
