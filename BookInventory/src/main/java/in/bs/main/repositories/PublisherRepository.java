package in.bs.main.repositories;

import in.bs.main.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublisherRepository extends JpaRepository <Publisher,Long>{
    Publisher findByName(String name);
}
