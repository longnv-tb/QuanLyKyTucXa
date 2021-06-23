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

import ktx.Room;
import ktx.data.RoomRepository;

@RestController
@RequestMapping(path="/ktxRoom", produces="application/json")
@CrossOrigin(origins="*")
public class RoomController {
	private RoomRepository roomRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public RoomController(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Room postRoom(@RequestBody Room room) {
		return roomRepo.save(room);
	}
	
	@GetMapping("/rooms")
	public Iterable<Room> getAllRooms(){
		return roomRepo.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRoom(@PathVariable("id") Long id) {
		try {
			roomRepo.deleteById(id);
		}catch(Exception e) {
			
		}
	}
	
	@PutMapping("/{id}")
	public Room putRoom(@RequestBody Room room) {
		return roomRepo.save(room);
	}
	
	@GetMapping("/searchBySoPhong/{soPhong}")
	public Iterable<Room> searchBySoPhong(@PathVariable("soPhong") String soPhong){
		return roomRepo.findBySoPhong(soPhong);
	}
	
	@GetMapping("searchById/{id}")
	public Room roomById(@PathVariable("id") Long id) {
		Optional<Room> optRoom = roomRepo.findById(id);
		if(optRoom.isPresent()) {
			return optRoom.get();
		}
		return null;	
	}
}
