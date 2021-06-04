package airconditionsapp.areas.users.services;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import airconditionsapp.areas.guides.entities.Guide;
//import airconditionsapp.areas.guides.services.GuideService;
import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.models.binding.EmailBindingModel;
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

	@Override
	public void sendEmail(EmailBindingModel model) throws AddressException, MessagingException, IOException {
		
		
		Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	           return new PasswordAuthentication("tasev.clima@gmail.com", "katerina93!");
	        }
	     });
	    
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

	    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tasev.clima@gmail.com"));
	    msg.setSubject(model.getEmail() + " " + model.getName());
	    msg.setContent(model.getMsg(), "text/html");
	    msg.setSentDate(new Date());

	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    messageBodyPart.setContent(model.getTitle()+" "+model.getMsg(), "text/html");

	    Multipart multipart = new MimeMultipart();
	    multipart.addBodyPart(messageBodyPart);
	    MimeBodyPart attachPart = new MimeBodyPart();

	   
	   // multipart.addBodyPart(attachPart);
	    msg.setContent(multipart);
	    Transport.send(msg);
		
		
	}

	
	
	

}
