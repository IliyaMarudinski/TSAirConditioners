package airconditionsapp.areas.articles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.articles.entities.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer>{
	Items findByName(String name);
	Items findById(int id);
	@Query(value = "select NAME from ITEMS", nativeQuery = true)
	public List<String> getAllItemNames();
}
