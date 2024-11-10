package in.bs.main.repositories;

import in.bs.main.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository  extends JpaRepository<Author,Long> {
    Author findByName(String name); // Method to find an author by name

}
