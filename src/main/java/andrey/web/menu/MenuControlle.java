package andrey.web.menu;

import andrey.to.MealMenu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = MenuControlle.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuControlle extends AbstractMenuController {
    static final String REST_URL = "/rest/menu";

    @Override
    @GetMapping("/today")
    public List<MealMenu> getToday() {
        return super.getToday();
    }

}