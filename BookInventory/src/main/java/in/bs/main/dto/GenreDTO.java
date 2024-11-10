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
@Schema(name="GenreDTO",description="It holds the details of  book Genre ")
public class GenreDTO {
    @NotEmpty(message = "Genre name cannot be empty")
    private String name;

    // Getters and setters
}
