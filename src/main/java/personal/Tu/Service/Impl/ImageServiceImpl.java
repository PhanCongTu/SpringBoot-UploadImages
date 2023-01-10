package personal.Tu.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import personal.Tu.Entity.Image;
import personal.Tu.Repository.IImageRepository;
import personal.Tu.Service.IImageService;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    IImageRepository imageRepository;

    public ImageServiceImpl(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public <S extends Image> S save(S entity) {
        // Kiểm tra sự tồn tại của image
        Optional<Image> optional = findById(entity.getID());
        if (optional.isPresent()) {
            if (StringUtils.isEmpty(entity.getImage())) {
                entity.setImage(optional.get().getImage());
            } else {
                entity.setImage(entity.getImage());
            }
        }
        return imageRepository.save(entity);
    }

    @Override
    public Optional<Image> findById(Integer integer) {
        return imageRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return imageRepository.existsById(integer);
    }

    @Override
    public long count() {
        return imageRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        imageRepository.deleteById(integer);
    }

    @Override
    public void delete(Image entity) {
        imageRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        imageRepository.deleteAll();
    }

    @Override
    public List<Image> findAll(Sort sort) {
        return imageRepository.findAll(sort);
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public List<Image> findByNameContaining(String name) {
        return imageRepository.findByNameContaining(name);
    }
}
