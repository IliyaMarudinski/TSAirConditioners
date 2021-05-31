package airconditionsapp.areas.articles.controller;

import airconditionsapp.areas.articles.model.binding.ConditionerAddModelBinding;
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
		model.addAttribute("conditioners", dataService.getAllAerConditioners());
		model.addAttribute("view",ViewConstants.AdminView);
		return ViewConstants.BaseLayoutView;
	}

	
	@PostMapping("add_service")
	public String addService(ServiceAddModelBinding model) {
		
		dataService.addService(model);
		
		return "redirect:/admin/articles" ;
	}

	@PostMapping("add_conditioner")
	public String addService(ConditionerAddModelBinding model) {

		dataService.addConditioner(model);

		return "redirect:/admin/articles" ;
	}
	@PostMapping("conditioner_update/{id}")
	public String updateCondition(ConditionerAddModelBinding model, @PathVariable Integer id) {
        model.setId(id);
		dataService.updateCondition(model);

		return "redirect:/admin/articles" ;
	}
	
	@GetMapping("service_delete/{id}")
	public String deleteService(@PathVariable Integer id) {
		
		dataService.deleteService(id);
		
		return "redirect:/admin/articles" ;
	}

	@GetMapping("conditioner_delete/{id}")
	public String deleteConditioner(@PathVariable Integer id) {

		dataService.deleteCondition(id);

		return "redirect:/admin/articles" ;
	}

	@GetMapping("service_update/{id}")
	public String updatedService(@PathVariable Integer id) {

//		dataService.deleteService(id);

		return "redirect:/admin/articles" ;
	}

	@GetMapping("conditioner_update/{id}")
	public String updateConditioner(@PathVariable Integer id, Model model) {
		model.addAttribute("conditionerId", id);
		model.addAttribute("conditioner",dataService.findAerConditionerById(id));
		model.addAttribute("brands",dataService.getAllBrands());
		model.addAttribute("view",ViewConstants.UpdateConditioner);
		return ViewConstants.BaseLayoutView;
	}

	@GetMapping("addconditioner")
	public String AdminAddConditioner(Model model) {
		model.addAttribute("brands",dataService.getAllBrands());
		model.addAttribute("view",ViewConstants.AddConditioner);
		return ViewConstants.BaseLayoutView;
	}

}
