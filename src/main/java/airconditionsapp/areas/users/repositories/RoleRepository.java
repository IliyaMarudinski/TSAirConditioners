package airconditionsapp.areas.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import airconditionsapp.areas.users.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
	
}
