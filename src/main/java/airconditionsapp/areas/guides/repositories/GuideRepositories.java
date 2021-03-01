package airconditionsapp.areas.guides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.guides.entities.Guide;
import airconditionsapp.areas.users.entities.User;

@Repository
public interface GuideRepositories extends JpaRepository<Guide, Integer> {
	
	Guide findByName(String name);
	Guide findByUserId(User userId);
	Guide findById(int id);

	
}
