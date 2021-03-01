package airconditionsapp.areas.users.services;

import airconditionsapp.areas.users.entities.Role;

public interface RoleService {
	
	Role findByName(String name);
}
