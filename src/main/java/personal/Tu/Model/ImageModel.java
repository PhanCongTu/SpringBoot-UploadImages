package personal.Tu.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {
    private int ID;

    @NotEmpty
    @Length(min = 5)
    private String name;
    private String image;
    private MultipartFile imageFile;
}
