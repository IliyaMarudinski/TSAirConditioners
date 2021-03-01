package airconditionsapp.areas.users.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import airconditionsapp.areas.users.models.binding.UserRegistrationBindingModel;

public interface SecurityUserService extends UserDetailsService {
	
	void register(UserRegistrationBindingModel bindingModel);
	
}
