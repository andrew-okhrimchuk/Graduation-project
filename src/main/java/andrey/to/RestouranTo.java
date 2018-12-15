package andrey.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class RestouranTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")
    @SafeHtml// https://stackoverflow.com/questions/17480809
    private String name;

    public RestouranTo() {
    }

    public RestouranTo(int id,
            @NotBlank @Size(min = 2, max = 100, message = "length must between 2 and 100 characters")@SafeHtml String name) {
        super(id);
        this.name = name;
    }
}
