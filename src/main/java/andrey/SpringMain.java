package andrey;

import andrey.repository.jpa.JpaHistoryMealRepositoryImpl;
import andrey.service.HistoryMealServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

//import ru.javawebinar.topjava.to.MealWithExceed;
//import ru.javawebinar.topjava.web.meal.MealRestController;
//import ru.javawebinar.topjava.web.user.AdminRestController;

/*
import static TestUtil.mockAuthorize;
import static UserTestData.USER;
*/

public class SpringMain {
  /*  public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            // appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-tools.xml");
            appCtx.refresh();

//            mockAuthorize(USER);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            //JpaHistoryMealRepositoryImpl xx = appCtx.getBean(JpaHistoryMealRepositoryImpl.class);
            HistoryMealServiceImpl xx = appCtx.getBean(HistoryMealServiceImpl.class);
           // System.out.println ("Печать бина = " + xx.getAll());
           /* AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", 1500, Role.ROLE_ADMIN));
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
    */
}
