package airconditionsapp.areas.gamedata.services;

import java.util.List;

import airconditionsapp.areas.gamedata.entities.Hero;
import airconditionsapp.areas.gamedata.entities.Items;
import airconditionsapp.areas.gamedata.entities.Runes;
import airconditionsapp.areas.gamedata.model.binding.HeroApiModelBinding;
import airconditionsapp.areas.gamedata.model.binding.ItemsApiModelBinding;
import airconditionsapp.areas.gamedata.model.binding.RuneAddModelBinding;

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
