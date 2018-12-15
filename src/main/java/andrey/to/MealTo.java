package andrey.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter @Setter @ToString
public class MealTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")
    @SafeHtml// https://stackoverflow.com/questions/17480809
    private String name;

    @Range(min = 1, max = 1000000)
    @NotNull
    private Integer restouran_id;

    @Range(min = 1, max = 1000000)
    @NotNull
    private Long cost;

    public MealTo() {
    }

    public MealTo(@NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")@SafeHtml String name,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters")Integer restouran_id,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters")Long cost) {
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }

    public MealTo(Integer id,
                  @NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters") @SafeHtml String name,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters") Integer restouran_id,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters") Long cost) {
        super(id);
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }

    public MealTo(@NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")@SafeHtml String name,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 10 characters") Integer restouran_id,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters") long cost) {
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }

    public MealTo(Integer id,
                  @NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters") @SafeHtml String name,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters") Integer restouran_id,
                  @NotNull @Range(min = 1, max = 1000000, message = "length must between 1 and 1000000 characters") long cost) {
        super(id);
        this.name = name;
        this.restouran_id = restouran_id;
        this.cost = cost;
    }
}
