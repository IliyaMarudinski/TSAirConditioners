package airconditionsapp.areas.articles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.articles.entities.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
	
	Hero findByName(String name);
	
	@Query(value = "select NAME from HEROES", nativeQuery = true)
	public List<String> getAllHeroesNames();
	
}
