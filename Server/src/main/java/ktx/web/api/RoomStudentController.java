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

import ktx.RoomStudent;
import ktx.data.RoomStudentRepository;

@RestController
@RequestMapping(path="/roomstudent", produces="application/json")
@CrossOrigin(origins="*")
public class RoomStudentController {
	private RoomStudentRepository roomRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public RoomStudentController(RoomStudentRepository roomRepo) {
		this.roomRepo = roomRepo;
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addStudentRoom(@RequestBody RoomStudent rs) {
		roomRepo.save(rs);
	}
	
	@GetMapping("/getById/{id}")
	public Iterable<RoomStudent> getByMSV(@PathVariable("id") Long id){
		return roomRepo.getStudentRoom(id);
	}
	
	
}
