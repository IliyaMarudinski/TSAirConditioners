package airconditionsapp.areas.articles.services;

import java.util.List;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.entities.Services;
import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;
import airconditionsapp.areas.guides.entities.Guide;

public interface EnvService {
	
	void addService(ServiceAddModelBinding model);
	void deleteService(int id);
	List<Services> getAllServices();
	List<AirConditioners> getAllAerConditioners();
	AirConditioners findAerConditionerById(int id);

}
