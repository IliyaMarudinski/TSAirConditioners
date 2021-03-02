package airconditionsapp.areas.articles.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airconditionsapp.areas.articles.entities.Hero;
import airconditionsapp.areas.articles.entities.HeroRole;
import airconditionsapp.areas.articles.entities.Items;
import airconditionsapp.areas.articles.entities.Runes;
import airconditionsapp.areas.articles.model.binding.HeroApiModelBinding;
import airconditionsapp.areas.articles.model.binding.HeroApiModelBinding.Data;
import airconditionsapp.areas.articles.model.binding.ItemsApiModelBinding;
import airconditionsapp.areas.articles.model.binding.RuneAddModelBinding;
import airconditionsapp.areas.articles.repositories.HeroRepository;
import airconditionsapp.areas.articles.repositories.HeroRoleRepository;
import airconditionsapp.areas.articles.repositories.ItemsRepository;
import airconditionsapp.areas.articles.repositories.RunesRepository;

@Service
public class GameDataServiceImpl implements GameDataService{

	private final HeroRepository     heroRepo;
	private final HeroRoleRepository roleRepo;
	private final RunesRepository    runeRepo;
	private final ItemsRepository    itemRepo;
	
	@Autowired
	public GameDataServiceImpl(HeroRepository heroRepo, HeroRoleRepository roleRepo, RunesRepository runeRepo,
			ItemsRepository itemRepo) {
		this.heroRepo = heroRepo;
		this.roleRepo = roleRepo;
		this.runeRepo = runeRepo;
		this.itemRepo = itemRepo;
	}

	@Override
	public void importHeroes(HeroApiModelBinding data) {
		
		List<String> heroes = heroRepo.getAllHeroesNames();

//    	System.out.println(data.getData().size());
//    	System.out.println(heroes.size());
		
		if(data.getData().size() != heroes.size()) {
			for (Map.Entry<String, Data> entry : data.getData().entrySet()) {
				Data values = entry.getValue();
				if(!heroes.contains(entry.getValue().getName())) {
					Hero hero = new Hero(values.getName(), values.getImg(), values.getBlurb(), values.getTitle());
                    for (String tag : values.getTags()) {
                        HeroRole role = roleRepo.findByName(tag);
                        if(role == null) {
                        	role = new HeroRole(tag);
                        	System.out.println(role.getName());
                        	roleRepo.save(role);
                        }
                        hero.addHeroRole(role);
					}
                    heroRepo.save(hero);
                      
				}		
				
			}
		}	
	}
	
    @Override
    public void importItems(ItemsApiModelBinding data) {
		
		List<String> items = itemRepo.getAllItemNames();
		List<String> apiItemsStr = new ArrayList<>();

		for (Map.Entry<String, airconditionsapp.areas.articles.model.binding.ItemsApiModelBinding.Data> entry : data.getData().entrySet()) {
			airconditionsapp.areas.articles.model.binding.ItemsApiModelBinding.Data values = entry.getValue();
			apiItemsStr.add(values.getName());
			if(!items.contains(entry.getValue().getName())) {
				Items item = new Items(values.getName(), entry.getKey().toString());
				System.out.println("!!!!!!!!!!!!!!!!!!" + item.getName());
                itemRepo.save(item);      
			}
		}

		for(String checkActive : items){
		   if(!apiItemsStr.contains(checkActive)) {
			   Items upItem = itemRepo.findByName(checkActive);
			   upItem.setActive(false);
			   itemRepo.save(upItem);
		   }
		}

	}


	@Override
	public void addRune(RuneAddModelBinding model) {
       Runes rune = new Runes(model.getName());
       runeRepo.save(rune);
		
	}


	@Override
	public List<Runes> getAllRunes() {
		return runeRepo.findAll();
	}


	@Override
	public void deleteRune(int id) {
		runeRepo.deleteById(id);
	}

	@Override
	public List<Hero> getAllHeroes() {
		return heroRepo.findAll();
	}

	@Override
	public List<Items> getAllItems() {
		return itemRepo.findAll();
	}

	@Override
	public Hero findHeroById(int id) {
		Hero hero = heroRepo.getOne(id);
		return hero;
	}
	
	

}
