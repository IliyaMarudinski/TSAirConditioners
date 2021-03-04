package airconditionsapp.areas.articles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import airconditionsapp.areas.guides.entities.Guide;

@Entity
@Table(name = "services")
public class Services {
	private int id;
	private String name;
	private int itemNum;
	private boolean active;
	private String description;
	private double price;

	public Services() {
	}

	public Services(String name, int itemNum, String description, double price) {
		this.id = id;
		this.name = name;
		this.itemNum = itemNum;
		this.active = true;
		this.description = description;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name ="itemNum")
	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
//TODO add nullable = false
	@Column(name = "active")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
//TODO add nullable = false
	@Column(name="description")
	@Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
