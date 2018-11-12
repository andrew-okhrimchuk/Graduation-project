package andrey.web.historyVoting;

import andrey.model.HistoryVoting;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequestMapping(value = HistoryVotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotingRestController extends AbstractHistoryVotingController {
    static final String REST_URL = "/rest/voting";

    @Override
    @GetMapping("/")
    public HistoryVoting getToday() {
        return super.getToday();
    }
    @Override
    @GetMapping("/{id}")
    public HistoryVoting getByDate(@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.getByDate(date);
    }

    @Override
    @PostMapping(path = "/{restouran_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HistoryVoting createVoting(@NotNull @NotEmpty @PathVariable("restouran_id") int restouran_id) {
        return super.createVoting(restouran_id);
    }
}