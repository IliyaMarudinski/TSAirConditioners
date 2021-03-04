package airconditionsapp.areas.articles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.articles.entities.Brands;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {
	
	Brands findByName(String name);
//	List<String> findNames();
	
}
