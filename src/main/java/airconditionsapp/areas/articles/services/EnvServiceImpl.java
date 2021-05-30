package airconditionsapp.areas.articles.services;

import java.util.List;

import airconditionsapp.areas.articles.entities.Brands;
import airconditionsapp.areas.articles.model.binding.ConditionerAddModelBinding;
import airconditionsapp.areas.articles.model.binding.ServiceAddModelBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public List<Services> getAllServices(Sort by) {
		return serviceRepo.findAll();
	}

	@Override
	public List<Brands> getAllBrands() {
		return brandsRepo.findAll();
	}


	@Override
	public void addService(ServiceAddModelBinding model) {
		Services service;
		service = new Services(model.getName(),
				model.getItemNum(),
				model.getDescription(),
				model.getPrice());
		serviceRepo.save(service);
	}
	@Override
	public void addConditioner(ConditionerAddModelBinding model) {
		AirConditioners condition;
		Brands brand = addBrand(model.getBrands());
		condition = new AirConditioners(
				model.getName(),
				model.getImg(),
				model.getDescription(),
				brand,
				model.getRoomVolume(),
				model.getWarrenty(),
				model.getPower(),
				model.getEnergyClass(),
				model.getOutSize(),
				model.getInSize(),
				model.getPrice(),
				model.getPromoPrice());
		airConditionerRepo.save(condition);
	}
	@Override
	public void updateCondition(ConditionerAddModelBinding model) {
		AirConditioners condition;
		Brands brand = addBrand(model.getBrands());
		condition = airConditionerRepo.findById(model.getId());
		condition.setName(model.getName());
		condition.setImg(model.getImg());
		condition.setDescription(model.getDescription());
		condition.setBrands(brand);
		condition.setRoomVolume(model.getRoomVolume());
		condition.setWarrenty(model.getWarrenty());
		condition.setPower(model.getPower());
		condition.setEnergyClass(model.getEnergyClass());
		condition.setOutSize(model.getOutSize());
		condition.setInSize(model.getInSize());
		condition.setPrice(model.getPrice());
		condition.setPromoPrice(model.getPromoPrice());
		airConditionerRepo.save(condition);
	}
	@Override
	public Brands addBrand(String brandName) {
		Brands brand;
		brand = brandsRepo.findByName(brandName);
		if(brand == null){
			brand = new Brands(brandName);
			brandsRepo.save(brand);
		}
		return brand;
	}

	@Override
	public void deleteService(int id) {
		serviceRepo.deleteById(id);
	}

	@Override
	public void deleteCondition(int id) {
		airConditionerRepo.deleteById(id);
	}

	@Override
	public List<AirConditioners> getAllAerConditioners() {
		return airConditionerRepo.findAll();
	}

	@Override
	public AirConditioners findAerConditionerById(int id) {
		AirConditioners airConditioners = airConditionerRepo.getOne(id);
//		System.out.print("Conditioner name !!! " + airConditioners.getName());
		return airConditioners;
	}

}
