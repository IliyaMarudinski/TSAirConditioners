package airconditionsapp.areas.users.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import airconditionsapp.areas.articles.entities.AirConditioners;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	private int id;	
	private String name;
	private String username;
	private String email;		
	private String avatarPath;
	private String password;	
	private Set<Role> roles = new HashSet<>();
	private Set<AirConditioners> favoriteAirConditioners = new HashSet<>();
	
	public User() {
	
	}
	
	public User(String name, String username, String email, String avatarPath, String password) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatarPath = "";	
	}	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "email", unique = true, nullable = false)	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "avatarPath")
	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles")
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.roles;
	}

	
	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<AirConditioners> getFavoriteAirConditioners() {
		return favoriteAirConditioners;
	}

	public void setFavoriteAirConditioners(Set<AirConditioners> favoriteHeroes) {
		this.favoriteAirConditioners = favoriteHeroes;
	}

	public void addFavoriteAirConditioners(AirConditioners cond) {
		this.favoriteAirConditioners.add(cond);
	}

	public void removeFavoriteAirConditioners(AirConditioners cond) {
		this.favoriteAirConditioners.remove(cond);
	}
}
