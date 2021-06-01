package airconditionsapp.areas.articles.controller;

//import airconditionsapp.areas.articles.model.binding.ServiceModelBinding;
import airconditionsapp.areas.articles.services.EnvService;
import airconditionsapp.constants.ViewConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("pages")
public class ArticlesController {

    private final EnvService dataService;

    @Autowired
    public ArticlesController(EnvService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("viewservices")
    public String ViewServices(Model model){

        model.addAttribute("services", dataService.getAllServices(Sort.by(Sort.Direction.ASC, "item_num")));
        model.addAttribute("view", ViewConstants.ViewService);
        return ViewConstants.BaseLayoutView;

    }
    @GetMapping("viewconditioners")
    public String ViewConditioners(Model model, @RequestParam(value = "brandsDropDown", required = false)String brandName,
                                   @RequestParam(value = "powerDropDown", required = false)String power){
        System.out.println("HERE!!!" + brandName);

        model.addAttribute("brands",dataService.getAllBrands());
        if(power==null || power.equals("Обем Стая") && brandName==null || brandName.equals("Филтрирай Марка Тук"))
            model.addAttribute("conditioners", dataService.getAllAerConditioners());
        else{
            model.addAttribute("conditioners", dataService.getAllAerConditionersByFilters(brandName, power));
            model.addAttribute("brandSelected", brandName);
            model.addAttribute("powerSelected", power);
        }
        model.addAttribute("view", ViewConstants.ViewConditioner);
        return ViewConstants.BaseLayoutView;

    }

    @GetMapping("conditionerpreview/{id}")
    public String deleteService(Model model, @PathVariable Integer id) {

        model.addAttribute("conditioner", dataService.findAerConditionerById(id));
        model.addAttribute("view", ViewConstants.ViewConditionerPreview);
        return ViewConstants.BaseLayoutView;
    }
}
