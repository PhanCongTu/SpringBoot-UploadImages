package personal.Tu.Service.Impl;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import personal.Tu.Config.StorageProperties;
import personal.Tu.Exception.StorageException;
import personal.Tu.Service.IStorageService;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements IStorageService {
    private final Path rootLocation;

    @Override
    public String getStorageFileName(MultipartFile file, int id){
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id + "," + ext;
    }
    public FileSystemStorageServiceImpl(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    @Override
    public void store(MultipartFile file, String storeFilename){
        try {
            if (file.isEmpty()){
                throw new StorageException("Không thể lưu file rỗng!");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                    .normalize().toAbsolutePath();  // Lấy đường dẫn tuyệt đối
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
                throw new StorageException("Không thể lưu file bên ngoài đường dẫn!");
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e){
            throw new StorageException("Không thể lưu file: ", e);
        }
    }
    @Override
    public Resource loadAsResource(String fileName){
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            throw new StorageException("Không thể đọc file: " + fileName);
        } catch (Exception e){
            throw new StorageException("Không thể đọc file: " + fileName);
        }
    }

    @Override
    public Path load(String fileName){
        return rootLocation.resolve(fileName);
    }

    @Override
    public void delete(String storeFileName) throws Exception {
        Path destinationFile = rootLocation.resolve(Paths.get(storeFileName)).normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }

    @Override
    public void init(){
        try {
            Files.createDirectories(rootLocation);

        }catch (Exception e){
            throw new StorageException("Không thể đọc file: " + e);
        }
    }
}
