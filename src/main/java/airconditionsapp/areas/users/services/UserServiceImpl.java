package airconditionsapp.areas.users.services;


import java.util.List;
import java.util.Set;

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
//	private final GuideService guideService;
	
	@Autowired
//	public UserServiceImpl(UserRepository userRepository, GuideService guideService) {
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
//		this.guideService = guideService;
	}
	
	@Override
	public User getUserByUsername(String username) {
		
		User user = this.userRepository.findByUsername(username);
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
	public Set<User> getFollowed(String userName) {

		User user = userRepository.findByUsername(userName); 
		
		
		return user.getFollowers();          
	}

	@Override
	public List<User> searchUserByName(String searchWord) {
		
		return userRepository.findByUsernameContaining(searchWord);
	}

	@Override
	public User getUserById(int id) {
		
		return userRepository.findById(id);
	}

//	@Override
//	public boolean addToFavorites(int guideId, String user) {
//
//		Guide guide = guideService.getGuide(guideId);
//		User loggedUser =userRepository.findByUsername(user);
//		String returnValue = userRepository.isGuideAlreadyExist(loggedUser.getId(), guideId);
//
//		if( returnValue != null) {
//
//			return false;
//		}
//
//		loggedUser.addFavoriteGuide(guide);
//
//		userRepository.save(loggedUser);
//
//		return true;
//	}
//
//	@Override
//	public void deleteFavorite(int id, String user) {
//
//		User loggedUser =userRepository.findByUsername(user);
//
//		userRepository.deleteFavorite(loggedUser.getId(), id);
//
//	}

}
