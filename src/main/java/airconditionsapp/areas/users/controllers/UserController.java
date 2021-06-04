package airconditionsapp.areas.users.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.models.binding.EmailBindingModel;
import airconditionsapp.areas.users.models.binding.UpdateProfileBindingModel;
import airconditionsapp.areas.users.services.UserService;
import airconditionsapp.constants.ViewConstants;

@Controller
@RequestMapping("user")
public class UserController {
	
	
	private final UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		
		this.userService = userService;
	}

	@PostMapping("profile")
	public String updateProfile(UpdateProfileBindingModel bindingModel, BindingResult result) {
		
//		if(result.hasErrors()) {
//			
//		}else {
//			
//		}
		
		userService.updateUser(bindingModel);
		
		return "redirect:/user/profile";
		
	}


	@RequestMapping("addToFavorites/{id}")
	public String addCondToFavorites(@PathVariable Integer id, Principal principal, RedirectAttributes redirectAttributes) {

		if(userService.addToFavorites(id, principal.getName())) {
			redirectAttributes.addFlashAttribute("msg","success");
		}else {
			redirectAttributes.addFlashAttribute("msg","warning");
		}
		return "redirect:/pages/conditionerpreview/" +id;
	}
	@RequestMapping("removeFromFavorites/{id}")
	public String deleteFavorites(@PathVariable Integer id, Principal principal, RedirectAttributes redirectAttributes) {

		userService.deleteFavorite(id, principal.getName());
		redirectAttributes.addFlashAttribute("msg","success");

		return "redirect:/pages/conditionerpreview/" +id;
	}
	
	@PostMapping("email")
	public String sendMail(EmailBindingModel model) throws AddressException, MessagingException, IOException {
		
		userService.sendEmail(model);
		
		return "redirect:/";
	}

}
