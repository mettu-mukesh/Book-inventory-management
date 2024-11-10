package in.bs.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use this for internal processing; exclude from DTOs

    private String description;

    private int pages;

    @OneToOne // One-to-one relationship with Book
    @JoinColumn(name = "book_id")
    private Book book;

    // Getters and setters
}
