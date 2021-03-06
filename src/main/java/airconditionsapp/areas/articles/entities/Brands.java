package airconditionsapp.areas.articles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="brands")
public class Brands {
    private int id;
    private String name;
    private Set<AirConditioners> airConditioners = new HashSet<AirConditioners>();
    
    public Brands() {
	}
    
	public Brands(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false, unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "brands")
	public Set<AirConditioners> getAirConditioners() {
		return airConditioners;
	}

	public void setAirConditioners(Set<AirConditioners> airConditioners) {
		this.airConditioners = airConditioners;
	}
}
