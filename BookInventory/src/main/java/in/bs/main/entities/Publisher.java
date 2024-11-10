package in.bs.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use this for internal processing; exclude from DTOs

    private String name;


    @NotNull(message = "Publisher date cannot be null")
    @PastOrPresent(message = "Publisher date must be in the past or present")
    @Column(nullable = true)  // Ensure this column can be null
    private LocalDate date;

    // Using LocalDate here
    @OneToMany(mappedBy = "publisher") // One publisher can publish many books
    private Set<Book> books;

    // Getters and setters
}

