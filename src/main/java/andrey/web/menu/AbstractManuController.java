package andrey.web.menu;

import andrey.service.RestouranService;
import andrey.to.MealMenu;
import andrey.util.threadLocal.ServiceBin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


public abstract class AbstractManuController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestouranService service;
    @Autowired
    private  ServiceBin serviceBin;

    public List<MealMenu>  getToday() {
        serviceBin.set_ThreadLoca_to_Bin();
        log.info("get Voting today = {} ", LocalDate.now());
        return service.getManuToday();
    }
}