package airconditionsapp.areas.articles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="hero_roles")
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

	@ManyToMany(mappedBy = "heroRoles")
	public Set<AirConditioners> getHeroes() {
		return airConditioners;
	}

	public void setHeroes(Set<AirConditioners> airConditioners) {
		this.airConditioners = airConditioners;
	}
    
}
