package airconditionsapp.areas.users.services;


import java.util.List;
import java.util.Set;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import airconditionsapp.areas.guides.entities.Guide;
//import airconditionsapp.areas.guides.services.GuideService;
import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.models.binding.UpdateProfileBindingModel;
import airconditionsapp.areas.users.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final EnvService articleService;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, EnvService articleService) {
		this.userRepository = userRepository;
		this.articleService = articleService;
	}
	
	@Override
	public User getUserByUsername(String username) {
		
		User user = this.userRepository.findByUsername(username);

		user.getFavoriteAirConditioners().forEach((e) -> { e.fillImageAray(); });

		return user; 
	}

	@Override
	public boolean updateUser(UpdateProfileBindingModel bindingModel) {
		
		User user = userRepository.findById(bindingModel.getId());
		
		user.setName(bindingModel.getName());
		user.setEmail(bindingModel.getEmail());
		
		userRepository.saveAndFlush(user);
		
		//userRepository.deleteById(id);;
		
		return true;
	}

	@Override
	public List<User> searchUserByName(String searchWord) {

		return userRepository.findByUsernameContaining(searchWord);
	}

	@Override
	public User getUserById(int id) {
		
		return userRepository.findById(id);
	}

	@Override
	public boolean addToFavorites(int id, String user) {

		AirConditioners cond = articleService.findAerConditionerById(id);
		User loggedUser =userRepository.findByUsername(user);

		if( loggedUser.getFavoriteAirConditioners().contains(cond) ) {

			return false;
		}

		loggedUser.addFavoriteAirConditioners(cond);

		userRepository.save(loggedUser);

		return true;
	}

	@Override
	public void deleteFavorite(int id, String user) {

		AirConditioners cond = articleService.findAerConditionerById(id);
		User loggedUser =userRepository.findByUsername(user);

		if( loggedUser.getFavoriteAirConditioners().contains(cond) ) {
			loggedUser.removeFavoriteAirConditioners(cond);
			userRepository.save(loggedUser);
		} else return;

	}

}
