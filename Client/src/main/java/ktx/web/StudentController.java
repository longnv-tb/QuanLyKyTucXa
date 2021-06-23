package ktx.web;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import ktx.Room;
import ktx.Service;
import ktx.Student;
import ktx.UsedService;
@Controller
public class StudentController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/studentHome")
	public String home(Model model) {
		List<Student> students = Arrays.asList(rest.getForObject("http://localhost:8080/ktx/students", Student[].class));
        model.addAttribute("students", students);
		return "studentHome";
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/ktx/student/search")
	public String search(@RequestParam("msv") String msv, Model model) {
	    if (StringUtils.isEmpty(msv)) {
	        return "redirect:/studentHome";
	    }
	    List<Student> students = Arrays.asList(rest.getForObject("http://localhost:8080/ktx/student/searchByMsv/{msv}", Student[].class, msv));
	    model.addAttribute("students", students);
	    return "studentHome";
	}
	
	@GetMapping("/ktx/student/add")
	public String add(Model model) {
		model.addAttribute("rooms", rest.getForObject("http://localhost:8080/ktxRoom/rooms", Room[].class));
	    model.addAttribute("student", new Student(null,null,null,null,null,null));
	    return "studentForm";
	}
	
	@PostMapping("/ktx/save")
	public String save(Student student, RedirectAttributes redirect) {	
	    rest.postForObject("http://localhost:8080/ktx",student, Student.class);
	    redirect.addFlashAttribute("successMessage", "Saved successfully!");
		System.out.println(student);
	    return "redirect:/studentHome";
	}
	
	@GetMapping("/ktx/student/{id}/edit")
	public String edit(@PathVariable("id") String id, Model model) {
		System.out.println(id);
	    model.addAttribute("student", rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id));
	    return "studentForm";
	}
	
	@GetMapping("/ktx/student/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		rest.delete("http://localhost:8080/ktx/student/delete/{id}", id);
	    redirect.addFlashAttribute("successMessage", "Deleted student successfully!");
	    return "redirect:/studentHome";
	}
	
	@GetMapping("/ktx/student/{id}/selectService")
	public String select(@PathVariable("id") String id, Model model, HttpSession ss) {
		Student s =  rest.getForObject("http://localhost:8080/ktx/student/search/{id}", Student.class, id);
		ss.setAttribute("sStudent", s);
		
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/ktxService/services", Service[].class));
		model.addAttribute("services", services);
		
		return "selectService";
	}
	
	@GetMapping("/ktx/student/{id}/viewService")
	public String viewService(@PathVariable("id") String id, Model model) {
		System.out.println(id);
	    model.addAttribute("usedServices", rest.getForObject("http://localhost:8080/usedService/getById/{id}", UsedService[].class, id));
	    return "viewServiceForm";
	}
}
