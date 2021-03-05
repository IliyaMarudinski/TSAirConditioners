package airconditionsapp.areas.articles.controller;

//import airconditionsapp.areas.articles.model.binding.ServiceModelBinding;
import airconditionsapp.areas.articles.services.EnvService;
import airconditionsapp.constants.ViewConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        model.addAttribute("services", dataService.getAllServices(Sort.by(Sort.Direction.DESC, "itemNum")));
        model.addAttribute("view", ViewConstants.ViewService);
        return ViewConstants.BaseLayoutView;

    }
}
