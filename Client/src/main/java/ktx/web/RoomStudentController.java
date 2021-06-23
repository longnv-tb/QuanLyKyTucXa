package ktx.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ktx.Room;
import ktx.RoomStudent;
import ktx.Student;

@Controller
public class RoomStudentController {
	private RestTemplate rest = new RestTemplate();
	@PostMapping("/room/addStudent")
	public String get(@RequestParam("slStudent") List<Long> ids, HttpSession ss) {
		Room r = (Room) ss.getAttribute("slRoom");
		for(Long id: ids) {
			Student s = rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id);
			RoomStudent rs = new RoomStudent(s,r);
			rest.postForObject("http://localhost:8080/roomstudent/add", rs, RoomStudent.class);
		}
		return "redirect:/roomHome";
	}
}