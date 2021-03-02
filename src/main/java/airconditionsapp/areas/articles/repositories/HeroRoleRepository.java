package airconditionsapp.areas.articles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.articles.entities.HeroRole;

@Repository
public interface HeroRoleRepository extends JpaRepository<HeroRole, Integer> {
	
	HeroRole findByName(String name);
//	List<String> findNames();
	
}
