package andrey.web.restouran;

import andrey.model.Restouran;
import andrey.to.RestouranTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestouranRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestouranRestController extends AbstractRestouranController {
    static final String REST_URL = "/rest/profile/restouran";

    @Override
    @GetMapping("/{id}")
    public Restouran get(@PathVariable("id") int id) {
        return super.get(id);
    }
    @Override
    @GetMapping("/")
    public List<Restouran> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated @RequestBody RestouranTo restouranTo, @PathVariable("id") int id) {
        super.update(restouranTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestouranTo> createWithLocation(@Validated @RequestBody RestouranTo restouranTo) {
        RestouranTo created = super.create(restouranTo);

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