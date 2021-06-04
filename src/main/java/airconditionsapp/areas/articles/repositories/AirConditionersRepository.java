package airconditionsapp.areas.articles.repositories;

import java.util.List;

import airconditionsapp.areas.articles.entities.AirConditioners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionersRepository extends JpaRepository<AirConditioners, Integer> {
	
	AirConditioners findByName(String name);
	AirConditioners findById(int id);
	List<AirConditioners> findByBrandsIdAndPower(int brandId, String power);
	List<AirConditioners> findByBrandsId(int brandId);
	List<AirConditioners> findByPower(String power);
	@Query(value = "SELECT DISTINCT ROOMVOLUME FROM AIRCONDITIONER ORDER BY ROOMVOLUME", nativeQuery = true)
	List<String> getAllRoomVolumes();
//DELETE * FROM USERS_FAVORITE_AIR_CONDITIONERS WHERE FAVORITE_AIR_CONDITIONERS_ID
}
