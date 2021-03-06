package airconditionsapp.areas.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import airconditionsapp.areas.users.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);	
	User findById(int id);
	List<User>findByUsernameContaining(String input);
	
	@Modifying
	@Transactional
	@Query(value = "delete from users_favorite_guides where users_id = :id and favorite_guides_id = :guideId", nativeQuery = true)
	void deleteFavorite(int id, int guideId);

}
