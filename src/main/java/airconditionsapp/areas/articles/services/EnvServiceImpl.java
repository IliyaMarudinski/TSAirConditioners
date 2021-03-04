package airconditionsapp.areas.articles.services;

import java.util.List;

import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airconditionsapp.areas.articles.entities.AirConditioners;
import airconditionsapp.areas.articles.entities.Services;
import airconditionsapp.areas.articles.repositories.AirConditionersRepository;
import airconditionsapp.areas.articles.repositories.BrandsRepository;
import airconditionsapp.areas.articles.repositories.ServicesRepository;

@Service
public class EnvServiceImpl implements EnvService {

	private final AirConditionersRepository airConditionerRepo;
	private final BrandsRepository brandsRepo;
	private final ServicesRepository serviceRepo;

	@Autowired
	public EnvServiceImpl(AirConditionersRepository airConditionerRepo, BrandsRepository brandsRepo, ServicesRepository serviceRepo) {
		this.airConditionerRepo = airConditionerRepo;
		this.brandsRepo = brandsRepo;
		this.serviceRepo = serviceRepo;
	}

	@Override
	public List<Services> getAllServices() {
		return serviceRepo.findAll();
	}


	@Override
	public void addService(ServiceAddModelBinding model) {
		Services service = new Services(model.getName(),
				model.getItemNum(),
				model.getDescription(),
				model.getPrice());
		serviceRepo.save(service);
	}

	@Override
	public void deleteService(int id) {
		serviceRepo.deleteById(id);
	}

	@Override
	public List<AirConditioners> getAllAerConditioners() {
		return airConditionerRepo.findAll();
	}

	@Override
	public AirConditioners findAerConditionerById(int id) {
		AirConditioners airConditioners = airConditionerRepo.getOne(id);
		return airConditioners;
	}

}
