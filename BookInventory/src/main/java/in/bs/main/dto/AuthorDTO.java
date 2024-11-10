package in.bs.main.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Schema(name="AuthorDTO",description="It holds the details of Author ")
public class AuthorDTO {

    @NotEmpty(message = "Author name must not be empty")
    private String name;

}