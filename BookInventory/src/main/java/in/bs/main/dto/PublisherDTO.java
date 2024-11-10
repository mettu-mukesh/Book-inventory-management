package in.bs.main.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Schema(name="PublisherDTO",description="It holds the details of  Publisher ")


public class PublisherDTO {
    @NotEmpty(message = "Publisher name cannot be empty")

    private String name;


    @NotNull(message = "Publisher date cannot be null")
    @PastOrPresent(message = "Publisher date must be in the past or present")
    private LocalDate date;


}
