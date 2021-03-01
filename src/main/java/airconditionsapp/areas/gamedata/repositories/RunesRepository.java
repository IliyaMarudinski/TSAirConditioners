package airconditionsapp.areas.gamedata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.gamedata.entities.Runes;

@Repository
public interface RunesRepository extends JpaRepository<Runes, Integer> {

	Runes findByName(String name); 
	Runes findById(int id);
	
}
