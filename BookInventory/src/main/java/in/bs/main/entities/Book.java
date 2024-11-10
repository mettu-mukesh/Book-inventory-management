package in.bs.main.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use this for internal processing; exclude from DTOs

    private String title;

    @ManyToOne // Many books can be written by one author
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne // Many books can be published by one publisher
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL) // One-to-one relationship with BookDetails
    private BookDetails bookDetails;

    @ManyToMany // Many books can belong to many genres
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private  Set<Genre> genres = new HashSet<>();;

    // Getters and setters
}

