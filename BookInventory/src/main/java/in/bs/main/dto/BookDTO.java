package in.bs.main.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Schema(name="BookDTO",description="It holds the details of Books ")

public class BookDTO {

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Valid
    private AuthorDTO author;

    @NotNull(message = "Publisher cannot be null")
    @Valid
    private PublisherDTO publisher; // Ensure PublisherDTO is validated

    @NotNull(message = "Book details cannot be null")
    @Valid
    private BookDetailsDTO bookDetails; // Ensure BookDetailsDTO is validated

    @NotEmpty(message = "Genres cannot be empty")
    @Valid
    private List<GenreDTO> genres = new ArrayList<>(); // List must not be empty
}
