package personal.Tu.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


public class ImageModel {
    private int ID;

    @NotEmpty
    @Length(min = 5)
    private String name;
    private String image;
    private MultipartFile imageFile;
    
    
	public ImageModel() {
		super();
	}
	public ImageModel(int iD, @NotEmpty @Length(min = 5) String name, String image, MultipartFile imageFile) {
		super();
		ID = iD;
		this.name = name;
		this.image = image;
		this.imageFile = imageFile;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
    
    
}
