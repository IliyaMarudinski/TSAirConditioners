package airconditionsapp.areas.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.entities.Brands;
import airconditionsapp.areas.articles.services.EnvService;


@RestController
public class RestControler {
	
	
	private final EnvService dataService;
	
	@Autowired
	public RestControler(EnvService dataService) {
			
		this.dataService = dataService;
		
	}
	
	
	@GetMapping("api/brands")
    public ResponseEntity<List<Brands>> getAllbrands(){       
					
		ResponseEntity<List<Brands>> response = new ResponseEntity<>(dataService.getAllBrands(),HttpStatus.OK);
		
        return response;

    }
	
	@GetMapping("api/conditioners")
    public ResponseEntity<List<AirConditioners>> ViewConditioners(@RequestParam(required = false)String brandName){       
		
		
		ResponseEntity<List<AirConditioners>> response;
		
		if(brandName == null) {
			response=new ResponseEntity<>(dataService.getAllAerConditioners(),HttpStatus.OK);
		}else {
			response=new ResponseEntity<>(dataService.getAllAerConditionersByFilters(brandName, "Обем Стая"),HttpStatus.OK);
		}
		
		
        return response;

    }
	
	@GetMapping(value = "api/images/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable String image ) throws IOException {
		System.out.println("IMAGE");
	    InputStream in = getClass()
	      .getResourceAsStream("/static/images/"+image);
	    
	    return IOUtils.toByteArray(in);
	}

}
