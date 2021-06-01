package airconditionsapp.areas.articles.repositories;

import java.util.List;

import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.articles.entities.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer>{
	Services findByName(String name);
	Services findById(int id);
	@Query(value = "select NAME from SERVICES", nativeQuery = true)
	List<String> getAllServicesNames();
	List<Services> findAllByOrderByItemNumDesc();
}
