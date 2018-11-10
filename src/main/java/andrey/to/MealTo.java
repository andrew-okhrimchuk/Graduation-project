package andrey.to;

import andrey.model.Restouran;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class MealTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")
    @SafeHtml
    private String name;

    @NotBlank
    @Size(min = 1, max = 10 , message = "length must between 1 and 10 characters")
    @SafeHtml // https://stackoverflow.com/questions/17480809
    private int restouran_id;

    @Size(min = 1, max = 32, message = "length must between 1 and 32 characters")
    private long cost;

    public MealTo() {
    }

    public MealTo(@NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")@SafeHtml String name,
                  @NotBlank @Size(min = 1, max = 10, message = "length must between 1 and 10 characters") @SafeHtml int restouran_id,
                  @Size(min = 1, max = 32, message = "length must between 1 and 32 characters") long cost) {
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }

    public MealTo(Integer id,
                  @NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters") @SafeHtml String name,
                  @NotBlank @Size(min = 1, max = 10, message = "length must between 1 and 10 characters") @SafeHtml int restouran_id,
                  @Size(min = 1, max = 32, message = "length must between 1 and 32 characters") long cost) {
        super(id);
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                ", restouran_id=" + restouran_id +
                ", cost=" + cost +
                '}';
    }
}
