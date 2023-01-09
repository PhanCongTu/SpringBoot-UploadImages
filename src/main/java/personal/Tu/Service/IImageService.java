package personal.Tu.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import personal.Tu.Entity.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {
    List<Image> findAll();

    <S extends Image> S save(S entity);

    Optional<Image> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Image entity);

    void deleteAll();

    List<Image> findAll(Sort sort);

    Page<Image> findAll(Pageable pageable);

    List<Image> findByNameContaining(String name);
}
