package airconditionsapp.areas.articles.repositories;

import java.util.List;

import airconditionsapp.areas.articles.entities.AirConditioners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionersRepository extends JpaRepository<AirConditioners, Integer> {
	
	AirConditioners findByName(String name);
	
//	@Query(value = "select NAME from airconditioner", nativeQuery = true)
//	public List<String> getAllConditionersNames();
	
}
