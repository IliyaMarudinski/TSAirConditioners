package airconditionsapp.areas.articles.controller;

//import airconditionsapp.areas.articles.model.binding.ServiceModelBinding;
import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.services.EnvService;
import airconditionsapp.areas.users.entities.User;
import airconditionsapp.areas.users.services.UserService;
import airconditionsapp.constants.ViewConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("pages")
public class ArticlesController {

    private final EnvService dataService;
    private final UserService uService;

    @Autowired
    public ArticlesController(EnvService dataService, UserService uService) {
        this.dataService = dataService;
        this.uService = uService;
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
        model.addAttribute("rVolume",dataService.getAllRoomVolume());
        model.addAttribute("showFilters", true);
        model.addAttribute("showEmptyList", false);
        if((power==null || power.equals("Обем Стая")) && (brandName==null || brandName.equals("Филтрирай Марка Тук")))
            model.addAttribute("conditioners", dataService.getAllAerConditioners());
        else{
            model.addAttribute("conditioners", dataService.getAllAerConditionersByFilters(brandName, power));
            model.addAttribute("brandSelected", brandName);
            model.addAttribute("powerSelected", power);
        }
        model.addAttribute("view", ViewConstants.ViewConditioner);
        return ViewConstants.BaseLayoutView;

    }

    @GetMapping("viewfavorites")
    public String ViewFavorites(Model model, @RequestParam(value = "brandsDropDown", required = false)String brandName,
                                   @RequestParam(value = "powerDropDown", required = false)String power,
                                   Principal principal){
        System.out.println("HERE!!!" + brandName);
        User user = uService.getUserByUsername(principal.getName());
        model.addAttribute("brands",dataService.getAllBrands());
        model.addAttribute("rVolume",dataService.getAllRoomVolume());
        model.addAttribute("showFilters", false);
        model.addAttribute("showEmptyList", (user.getFavoriteAirConditioners().size()==0));
        model.addAttribute("conditioners", user.getFavoriteAirConditioners());

        model.addAttribute("view", ViewConstants.ViewConditioner);
        return ViewConstants.BaseLayoutView;

    }


    @GetMapping("conditionerpreview/{id}")
    public String deleteService(Model model, @PathVariable Integer id, Principal principal) {
        if(principal!=null){
            User user = uService.getUserByUsername(principal.getName());
            AirConditioners cond = dataService.findAerConditionerById(id);
            model.addAttribute("inFavorite", user.getFavoriteAirConditioners().contains(cond));
        }else{
            model.addAttribute("inFavorite", false);
        }

        model.addAttribute("conditioner", dataService.findAerConditionerById(id));
        model.addAttribute("view", ViewConstants.ViewConditionerPreview);
        return ViewConstants.BaseLayoutView;
    }
}
