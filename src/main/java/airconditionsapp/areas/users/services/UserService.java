package airconditionsapp.areas.users.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.models.binding.EmailBindingModel;
import airconditionsapp.areas.users.models.binding.UpdateProfileBindingModel;

public interface UserService {
	
	User getUserByUsername(String username);
	User getUserById(int id);
	boolean updateUser(UpdateProfileBindingModel bindingModel);
	List<User> searchUserByName(String searchWord);
	boolean addToFavorites(int id, String user);
	void deleteFavorite(int id, String user);
	void sendEmail(EmailBindingModel model) throws AddressException, MessagingException, IOException;
}
