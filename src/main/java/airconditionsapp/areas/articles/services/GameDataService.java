package airconditionsapp.areas.articles.services;

import java.util.List;

import airconditionsapp.areas.articles.entities.Hero;
import airconditionsapp.areas.articles.entities.Items;
import airconditionsapp.areas.articles.entities.Runes;
import airconditionsapp.areas.articles.model.binding.HeroApiModelBinding;
import airconditionsapp.areas.articles.model.binding.ItemsApiModelBinding;
import airconditionsapp.areas.articles.model.binding.RuneAddModelBinding;

public interface GameDataService {
	
	void importHeroes (HeroApiModelBinding data);
	void importItems  (ItemsApiModelBinding data);
	void addRune(RuneAddModelBinding model);
	void deleteRune(int id);
	Hero findHeroById(int id);
	List<Runes> getAllRunes();
	List<Hero>  getAllHeroes();
	List<Items> getAllItems();
	
}
