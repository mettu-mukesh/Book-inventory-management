package in.bs.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id; // Use this for internal processing; exclude from DTOs

    private String name;

    @OneToMany(mappedBy = "author") // One author can write many books
    private Set<Book> books;

    // Getters and setters
}
