package ktx.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ktx.Service;
import ktx.Student;
import ktx.UsedService;

@Controller
public class UsedServiceController {
	private RestTemplate rest = new RestTemplate();
	@PostMapping("/service/select")
	public String get(@RequestParam("usedService") List<Long> maDichvu, HttpSession ss) {
		Student stu = (Student) ss.getAttribute("sStudent");
		for(Long madv: maDichvu) {
			Service s = rest.getForObject("http://localhost:8080/ktxService/search/{maDichVu}", Service.class, madv);
			UsedService uService = new UsedService(stu,s);
			rest.postForObject("http://localhost:8080/usedService/add", uService, UsedService.class);
		}
		return "redirect:/studentHome";
	}
}
