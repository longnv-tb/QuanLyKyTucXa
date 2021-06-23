package ktx.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ktx.UsedService;
import ktx.data.UsedServiceRepository;

@RestController
@RequestMapping(path="/usedService", produces="application/json")
@CrossOrigin(origins="*")
public class UsedServiceController {
	private UsedServiceRepository usedRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public UsedServiceController( UsedServiceRepository usedRepo) {
		this.usedRepo = usedRepo;
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addUsed(@RequestBody UsedService u) {
		usedRepo.save(u);
	}
	
	@GetMapping("/getById/{MSV}")
	public Iterable<UsedService> getByMSV(@PathVariable("MSV") String msv){
		return usedRepo.getUsedServiceById(msv);
	}
}
