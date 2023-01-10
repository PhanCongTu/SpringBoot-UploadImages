package personal.Tu.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IStorageService {

    String getStorageFileName(MultipartFile file, String id);

    void store(MultipartFile file, String storeFilename);


    Resource loadAsResource(String fileName);

    Path load(String fileName);

    void delete(String storeFileName) throws Exception;

    void init();
}
