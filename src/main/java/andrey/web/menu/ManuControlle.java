package andrey.web.menu;

import andrey.to.MealMenu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ManuControlle.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ManuControlle extends AbstractManuController {
    public static final String REST_URL = "/rest/menu";

    @Override
    @GetMapping("/")
    public List<MealMenu> getToday() {
        return super.getToday();
    }

}