package airconditionsapp.areas.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import airconditionsapp.areas.articles.model.binding.HeroApiModelBinding;
import airconditionsapp.areas.articles.model.binding.ItemsApiModelBinding;
import airconditionsapp.areas.articles.model.binding.RuneAddModelBinding;
import airconditionsapp.areas.articles.services.GameDataService;
import airconditionsapp.constants.ViewConstants;


@Controller
@RequestMapping("admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ArticlesController {
	
	private final GameDataService dataService;
	
	@Autowired
	public ArticlesController(GameDataService dataService) {
		this.dataService = dataService;
	}

	@GetMapping("articles")
	public String AdminArticles(Model model) {
		
		model.addAttribute("runes",dataService.getAllRunes());
		model.addAttribute("view",ViewConstants.AdminView);
		return ViewConstants.BaseLayoutView;
	}
	
	@GetMapping("update_heroes")
	public String updateHeroes(Model model) {
		
		String url = "http://ddragon.leagueoflegends.com/cdn/10.25.1/data/en_US/champion.json";
		RestTemplate heroesJson = new RestTemplate();
		HeroApiModelBinding result = heroesJson.getForObject(url, HeroApiModelBinding.class);
		dataService.importHeroes(result);
		
		model.addAttribute("view",ViewConstants.AdminView);
		return ViewConstants.BaseLayoutView;
	}
	
	@GetMapping("update_items")
	public String updateItems(Model model) {
		
		String url = "http://ddragon.leagueoflegends.com/cdn/10.25.1/data/en_US/item.json";
		RestTemplate itemsJson = new RestTemplate();
		ItemsApiModelBinding result = itemsJson.getForObject(url, ItemsApiModelBinding.class);
		dataService.importItems(result);
		
		model.addAttribute("view",ViewConstants.AdminView);
		return ViewConstants.BaseLayoutView;
	}	
	
	@PostMapping("add_rune")
	public String addRune(RuneAddModelBinding model) {
		
		dataService.addRune(model);
		
		return "redirect:/admin/editgamedata" ;
	}
	
	@GetMapping("rune_delete/{id}")
	public String deleteRune(@PathVariable Integer id) {
		
		dataService.deleteRune(id);
		
		return "redirect:/admin/editgamedata" ;
	}

}
