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

import ktx.Guest;
import ktx.data.GuestRepository;

@RestController
@RequestMapping(path="/guest", produces="application/json")
@CrossOrigin(origins="*")
public class GuestController {
	private GuestRepository guestRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public GuestController(GuestRepository guestRepo) {
		this.guestRepo = guestRepo;
	}
	
	@GetMapping("/guests")
	public Iterable<Guest> getAllRooms(){
		return guestRepo.findAll();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Guest postGuest(@RequestBody Guest guest) {
		return guestRepo.save(guest);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteGuest(@PathVariable("id") Long id) {
		try {
			guestRepo.deleteById(id);
		}catch(Exception e) {
			
		}
	}
	
	@PutMapping("/edit/{id}")
	public Guest putGuest(@RequestBody Guest guest) {
		return guestRepo.save(guest);
	}
	

	@GetMapping("/searchByTenKhach/{tenKhach}")
	public Iterable<Guest> searchByTen(@PathVariable("tenKhach") String tenKhach){
		return guestRepo.findByTen(tenKhach);
	}
	
	@GetMapping("searchById/{id}")
	public Guest guestById(@PathVariable("id") Long id) {
		Optional<Guest> optGuest = guestRepo.findById(id);
		if(optGuest.isPresent()) {
			return optGuest.get();
		}
		return null;	
	}
}
