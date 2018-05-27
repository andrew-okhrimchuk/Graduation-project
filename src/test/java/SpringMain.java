import org.springframework.context.support.GenericXmlApplicationContext;
import model.Role;
import model.User;
//import ru.javawebinar.topjava.to.MealWithExceed;
//import ru.javawebinar.topjava.web.meal.MealRestController;
//import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

/*
import static TestUtil.mockAuthorize;
import static UserTestData.USER;
*/

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/mock.xml");
            appCtx.refresh();

//            mockAuthorize(USER);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
           /* AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", 1500, Role.ROLE_ADMIN));
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);*/
        }
    }
}
