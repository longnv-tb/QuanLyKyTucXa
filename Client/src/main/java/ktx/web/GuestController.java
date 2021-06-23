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

import ktx.Guest;
import ktx.Student;

@Controller
public class GuestController {
	private RestTemplate rest = new RestTemplate();
	
	@RequestMapping("/guestHome")
	public String home(Model model) {
		List<Guest> guests = Arrays.asList(rest.getForObject("http://localhost:8080/guest/guests", Guest[].class));
		model.addAttribute("guests", guests);
		return "guestHome";
	}
	
	@GetMapping("/guest/add")
	public String add(Model model) {
		List<Student> students = Arrays.asList(rest.getForObject("http://localhost:8080/ktx/students", Student[].class));
        model.addAttribute("students", students);
		return "guestForm";
	}
	
	@GetMapping("/guest/{id}/add")
	public String showAddForm(@PathVariable("id") String id, Model model, HttpSession session) {
		System.out.println(id);
		Student student = rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id);
		session.setAttribute("selectedStudent", student);
		model.addAttribute("guest", new Guest(null, null, null,null));
		return "guestForm";
	}
	
//	@PostMapping("/guest/save")
//	public String save(@RequestParam("cmt") String cmt, @RequestParam("ten") String ten, @RequestParam("ngaySinh") String ngaySinh, @RequestParam("ngayDenChoi") String ngayDenChoi, @RequestParam("id") Long id, RedirectAttributes redirect) {
//		Guest g = new Guest(cmt,ten,ngaySinh, ngayDenChoi, rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id));
//		System.out.println(g);
//		rest.postForObject("http://localhost:8080/guest", g, Guest.class);
//		redirect.addFlashAttribute("successMessage", "Save successfully");
//		return "redirect:/guestHome";
//	}
	
	@PostMapping("/guest/save")
	public String save(Guest guest, RedirectAttributes redirect, HttpSession session) {
//		Guest g = new Guest(cmt,ten,ngaySinh, ngayDenChoi, rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id));
		Student student = (Student)session.getAttribute("selectedStudent");
		guest.setStudent(student);
		rest.postForObject("http://localhost:8080/guest", guest, Guest.class);
		redirect.addFlashAttribute("successMessage", "Save successfully");
		return "redirect:/guestHome";
	}
	
	@GetMapping("/guest/search")
	public String search(@RequestParam("tenKhach") String tenKhach, Model model) {
		if(StringUtils.isEmpty(tenKhach)) {
			return "redirect:/guestHome";
		}
		List<Guest> guests = Arrays.asList(rest.getForObject("http://localhost:8080/guest/searchByTenKhach/{tenKhach}", Guest[].class, tenKhach));
		model.addAttribute("guests", guests);
		return "guestHome";
	}
	
	@GetMapping("/guest/{id}/edit")
	public String edit(@PathVariable("id") String id, Model model, HttpSession session) {
		Guest g = rest.getForObject("http://localhost:8080/guest/searchById/{id}", Guest.class, id);
		System.out.println(g);
		model.addAttribute("guest", g);
		session.setAttribute("selectedStudent", g.getStudent());
		return "guestForm";
	}
	
	@GetMapping("/guest/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		rest.delete("http://localhost:8080/guest/delete/{id}", id);
		redirect.addFlashAttribute("successMessage", "Deleted guest successfully");
		return "redirect:/guestHome";
	}
	
}
