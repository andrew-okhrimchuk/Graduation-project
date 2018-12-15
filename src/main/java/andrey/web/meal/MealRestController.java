package andrey.web.meal;

import andrey.model.Meal;
import andrey.to.MealTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/meals";


    @GetMapping("/{id}")
    public Meal get(@PathVariable("id")int id) {
        return super.get(id, null);
    }

    @GetMapping("/{id}/{date}")
    public Meal getWithDate(@PathVariable("id")int id, @PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.get(id, date);
    }

    @Override
    @GetMapping("/restouran/{id}")
    public List<Meal> getAll(@PathVariable("id") int restouranId) {
        return super.getAll(restouranId);
    }


    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated @RequestBody MealTo meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MealTo> createWithLocation(@Validated @RequestBody MealTo meal) {
        MealTo created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}