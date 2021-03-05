package airconditionsapp.areas.articles.controller;

import airconditionsapp.areas.articles.services.EnvService;
import airconditionsapp.constants.ViewConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;


@Controller
@RequestMapping("admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminArticlesController {
	
	private final EnvService dataService;
	
	@Autowired
	public AdminArticlesController(EnvService dataService) {
		this.dataService = dataService;
	}

	@GetMapping("articles")
	public String AdminArticles(Model model) {
		
		model.addAttribute("services",dataService.getAllServices(Sort.by(Sort.Direction.DESC, "itemNum")));
		model.addAttribute("view",ViewConstants.AdminView);
		return ViewConstants.BaseLayoutView;
	}

	
	@PostMapping("add_service")
	public String addService(ServiceAddModelBinding model) {
		
		dataService.addService(model);
		
		return "redirect:/admin/articles" ;
	}
	
	@GetMapping("service_delete/{id}")
	public String deleteService(@PathVariable Integer id) {
		
		dataService.deleteService(id);
		
		return "redirect:/admin/articles" ;
	}

	@GetMapping("service_update/{id}")
	public String updatedService(@PathVariable Integer id) {

//		dataService.deleteService(id);

		return "redirect:/admin/articles" ;
	}

}
