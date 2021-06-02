package airconditionsapp.areas.articles.entities;

import javax.persistence.*;

@Entity
@Table(name = "airconditioner")
public class AirConditioners {
	private int id;
	private String name;
	private String img;
	private String description;
	private Brands brands;
	private int roomVolume;
	private double warrenty;
	private String power;
	private String energyClass;
	private String outSize;
	private String inSize;
	private double price;
	private double promoPrice;
	
	public AirConditioners() {
	}

	public AirConditioners(String name, String img, String description, Brands brands, int roomVolume, double warenty, String power, String energyClass, String outSize, String inSize, double price, double promoPrice) {
		this.name = name;
		this.img = img;
		this.description = description;
		this.brands = brands;
		this.roomVolume = roomVolume;
		this.warrenty = warenty;
		this.power = power;
		this.energyClass = energyClass;
		this.outSize = outSize;
		this.inSize = inSize;
		this.price = price;
		this.promoPrice = promoPrice;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "promoprice")
	public double getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(double promoPrice) {
		this.promoPrice = promoPrice;
	}
	@Column(name = "roomvolume")
	public int getRoomVolume() {
		return roomVolume;
	}

	public void setRoomVolume(int roomVolume) {
		this.roomVolume = roomVolume;
	}
	@Column(name = "warrenty")
	public double getWarrenty() {
		return warrenty;
	}

	public void setWarrenty(double warenty) {
		this.warrenty = warenty;
	}
	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name = "power")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	@Column(name = "energyclass")
	public String getEnergyClass() {
		return energyClass;
	}

	public void setEnergyClass(String energyClass) {
		this.energyClass = energyClass;
	}

	@Column(name = "outsize")
	public String getOutSize() {
		return outSize;
	}

	public void setOutSize(String outSize) {
		this.outSize = outSize;
	}

	@Column(name = "insize")
	public String getInSize() {
		return inSize;
	}

	public void setInSize(String inSize) {
		this.inSize = inSize;
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
   
}
