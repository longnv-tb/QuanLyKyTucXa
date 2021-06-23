package ktx.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ktx.Service;
import ktx.data.ServiceRepository;

@RestController
@RequestMapping(path="/ktxService", produces="application/json")
@CrossOrigin(origins="*")
public class ServiceController {
	private ServiceRepository serviceRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public ServiceController(ServiceRepository serviceRepo) {
		this.serviceRepo = serviceRepo;
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Service postService(@RequestBody Service service) {
		return serviceRepo.save(service);
	}
	
	@GetMapping("/services")
	public Iterable<Service> getAllServices(){
		return serviceRepo.findAll();
	}
	
	
	@GetMapping("/search/{maDichVu}")
	public Service serviceById(@PathVariable("maDichVu") Long maDichVu) {
		Optional<Service> optService = serviceRepo.findById(maDichVu);
		if(optService.isPresent()) {
			return optService.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{maDichVu}")
	public void deleteService(@PathVariable("maDichVu") Long maDichVu) {
		try {
			serviceRepo.deleteById(maDichVu);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/edit/{maDichVu}")
	public Service putService(@RequestBody Service service) {
		return serviceRepo.save(service);
	}
	
	@GetMapping("/searchByTenDichVu/{tenDichVu}")
	public Iterable<Service> searchByTen(@PathVariable("tenDichVu") String tenDichVu){
		return serviceRepo.findByTen(tenDichVu);
	}
	
}
