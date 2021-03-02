package airconditionsapp.areas.guides.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airconditionsapp.areas.articles.repositories.ItemsRepository;
import airconditionsapp.areas.articles.repositories.RunesRepository;
import airconditionsapp.areas.guides.entities.Guide;
import airconditionsapp.areas.guides.model.binding.UpdateGuideBindingModel;
import airconditionsapp.areas.guides.repositories.GuideRepositories;
import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.repositories.UserRepository;

@Service
public class GuideServiceImpl implements GuideService {

	private final GuideRepositories guideRepo;
	private final ItemsRepository   itemsRepo;
	private final RunesRepository   runeRepo;
	private final UserRepository    userRepo;
	
	@Autowired
	public GuideServiceImpl(GuideRepositories guideRepo, ItemsRepository itemsRepo, RunesRepository runeRepo, UserRepository userRepo) {
		this.guideRepo = guideRepo;
		this.itemsRepo = itemsRepo;
		this.runeRepo  = runeRepo;
		this.userRepo  = userRepo;
	}


	@Override
	public void newGuide(UpdateGuideBindingModel guide) {
		
		Guide newGuide = new Guide();
		User user = userRepo.findByUsername(guide.getUserId());
		
		newGuide.setName(guide.getName());
		newGuide.setDescription(guide.getDescription());
		newGuide.setMaxAbility(guide.getMaxAbility());
		newGuide.setStartWithAbility(guide.getStartWithAbility());
		newGuide.setUser(user);
		newGuide.setHeroId(guide.getHeroId());
	//	newGuide.getUserId()
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getItems().size());
		for(int item : guide.getItems()) {
			newGuide.addItem(itemsRepo.findById(item));
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getRune());
		newGuide.addRune(runeRepo.findById(guide.getRune()));

		user.addGuideToUser(newGuide);
		guideRepo.save(newGuide);
		
	}


	@Override
	public Guide getGuide(int id) {
		Guide guide = guideRepo.findById(id);
		return guide;
	}


	@Override
	public void updateGuide(UpdateGuideBindingModel guide) {
		
		Guide newGuide = guideRepo.findById(guide.getId());
		User user = userRepo.findByUsername(guide.getUserId());
		
		newGuide.setName(guide.getName());
		newGuide.setDescription(guide.getDescription());
		newGuide.setMaxAbility(guide.getMaxAbility());
		newGuide.setStartWithAbility(guide.getStartWithAbility());
		newGuide.setUser(user);
		newGuide.setHeroId(guide.getHeroId());
	//	newGuide.getUserId()
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getItems().size());
        newGuide.clearItems();
        newGuide.clearRunes();
		for(int item : guide.getItems()) {
			newGuide.addItem(itemsRepo.findById(item));
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getRune());
		newGuide.addRune(runeRepo.findById(guide.getRune()));

		user.addGuideToUser(newGuide);
		guideRepo.save(newGuide);
		
	}


	@Override
	public void deleteGuide(int id) {		
		guideRepo.deleteById(id);
		
	}


}
