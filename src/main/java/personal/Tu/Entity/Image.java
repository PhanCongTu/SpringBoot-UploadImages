package personal.Tu.Entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "name", columnDefinition = "nvarchar(200)")
	private String name;
	
	@Column(name = "image")
	private String image;

	public Image() {
		super();
	}

	public Image(int iD, String name, String image) {
		super();
		ID = iD;
		this.name = name;
		this.image = image;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
