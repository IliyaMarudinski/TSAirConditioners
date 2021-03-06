package airconditionsapp.areas.articles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "airconditioner")
public class AirConditioners {
	private int id;
	private String name;
	private String img;
	private String description;
	private String shortDescription;
	private Brands brands;
	
	public AirConditioners() {
	}

	public AirConditioners(String name, String img, String description, String shortDescription, Brands brands) {
		this.name = name;
		this.img = img;
		this.description = description;
		this.shortDescription = shortDescription;
		this.brands = brands;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	public Brands getBrands() {
		return brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}
	
	@Column(name = "img")
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "description")
	@Lob
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "shortdescription")
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
   
}
