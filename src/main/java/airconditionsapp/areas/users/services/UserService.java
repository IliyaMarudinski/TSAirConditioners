package airconditionsapp.areas.users.services;

import java.util.List;
import java.util.Set;

import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.models.binding.UpdateProfileBindingModel;

public interface UserService {
	
	User getUserByUsername(String username);
	User getUserById(int id);
	boolean updateUser(UpdateProfileBindingModel bindingModel);
	Set<User> getFollowed(String userName);
	List<User> searchUserByName(String searchWord);
	boolean addToFavorites(int guideId, String user);
	void deleteFavorite(int id, String user);

}
