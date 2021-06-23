package ktx.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import ktx.Room;
import ktx.RoomStudent;
import ktx.Student;

@Controller
public class RoomController {
	private RestTemplate rest = new RestTemplate();
	
	@RequestMapping("/roomHome")
	public String home(Model model) {
		List<Room> rooms = Arrays.asList(rest.getForObject("http://localhost:8080/ktxRoom/rooms", Room[].class));
		model.addAttribute("rooms", rooms);
		return "roomHome";
	}
	
	@GetMapping("/ktxRoom/search")
	public String search(@RequestParam("soPhong") String soPhong, Model model) {
		if(StringUtils.isEmpty(soPhong)) {
			return "redirect:/roomHome";
		}
		List<Room> rooms = Arrays.asList(rest.getForObject("http://localhost:8080/ktxRoom/searchBySoPhong/{soPhong}", Room[].class, soPhong));
		model.addAttribute("rooms", rooms);
		return "roomHome";
	}
	
	@GetMapping("/ktxRoom/add")
	public String add(Model model) {
		model.addAttribute("room", new Room(null, null, null, null));
		return "roomForm";
	}
	
	@PostMapping("/ktxRoom/save")
	public String save(Room room, RedirectAttributes redirect) {
		rest.postForObject("http://localhost:8080/ktxRoom", room, Room.class);
		redirect.addFlashAttribute("successMessage", "Save successfully");
		return "redirect:/roomHome";
	}
	
	@GetMapping("/ktxRoom/{id}/edit")
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("room", rest.getForObject("http://localhost:8080/ktxRoom/searchById/{id}", Room.class, id));
		return "roomForm";
	}
	
	@GetMapping("/ktxRoom/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		rest.delete("http://localhost:8080/ktxRoom/delete/{id}", id);
		redirect.addFlashAttribute("successMessage", "Deleted room successfully");
		return "redirect:/roomHome";
	}
	
	@GetMapping("/ktxRoom/{id}/addStudent")
	public String addStudent(@PathVariable("id") String id, HttpSession session, Model model) {
		session.setAttribute("slRoom", rest.getForObject("http://localhost:8080/ktxRoom/searchById/{id}", Room.class, id));
		model.addAttribute("students",rest.getForObject("http://localhost:8080/ktx/student/getFreeStudent", Student[].class));
		return "slStudentForm";
	}
	
	@GetMapping("/ktxRoom/{id}/viewStudent")
	public String addStudent(@PathVariable("id") String id, Model model) {
		model.addAttribute("students",rest.getForObject("http://localhost:8080/roomstudent/getById/{id}", RoomStudent[].class, id));
		return "viewStudentForm";
	}
	
}
