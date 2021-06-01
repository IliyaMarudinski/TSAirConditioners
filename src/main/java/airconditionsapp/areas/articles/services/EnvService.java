package airconditionsapp.areas.articles.services;

import java.util.List;
import java.util.concurrent.locks.Condition;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.entities.Brands;
import airconditionsapp.areas.articles.entities.Services;
import airconditionsapp.areas.articles.model.binding.ConditionerAddModelBinding;
import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;
import org.springframework.data.domain.Sort;

public interface EnvService {
	
	void addService(ServiceAddModelBinding model);
	void deleteService(int id);
	void addConditioner(ConditionerAddModelBinding model);
	void deleteCondition(int id);
	void updateCondition(ConditionerAddModelBinding model);
	Brands addBrand(String brandName);
	List<Services> getAllServices(Sort by);
	List<Brands> getAllBrands();
	List<AirConditioners> getAllAerConditioners();
	List<AirConditioners> getAllAerConditionersByFilters(String brandName, String power);
	AirConditioners findAerConditionerById(int id);

}
