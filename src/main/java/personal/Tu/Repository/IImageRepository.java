package personal.Tu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.Tu.Entity.Image;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByNameContaining(String name);
}
