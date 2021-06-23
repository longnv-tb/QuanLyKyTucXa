package ktx.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ktx.Service;

@Controller
public class ServiceController {
	private RestTemplate rest = new RestTemplate();
	
	@RequestMapping("/serviceHome")
	public String home(Model model) {
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/ktxService/services", Service[].class));
		model.addAttribute("services", services);
		return "serviceHome";
	}
	
	@GetMapping("/ktxService/search")
	public String search(@RequestParam("tenDichVu") String tenDichVu, Model model) {
		if(StringUtils.isEmpty(tenDichVu)) {
			return "redirect:/serviceHome";
		}
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/ktxService/searchByTenDichVu/{tenDichVu}", Service[].class, tenDichVu));
		model.addAttribute("services", services);
		return "serviceHome";
	}
	
	@GetMapping("/ktxService/add")
	public String showAddForm(Model model) {
		model.addAttribute("service", new Service(null, null));
		return "serviceForm";
	}
	
	@PostMapping("/ktxService/save")
	public String save(Service service, RedirectAttributes redirect) {
		rest.postForObject("http://localhost:8080/ktxService", service, Service.class);
		redirect.addFlashAttribute("successMessage", "Save successfully");
		return "redirect:/serviceHome";
	}
	
	@GetMapping("/ktxService/{maDichVu}/edit")
	public String edit(@PathVariable("maDichVu") String maDichVu, Model model) {
		model.addAttribute("service", rest.getForObject("http://localhost:8080/ktxService/search/{maDichVu}", Service.class, maDichVu));
		return "serviceForm";
	}
	
	@GetMapping("/ktxService/{maDichVu}/delete")
	public String delete(@PathVariable String maDichVu, RedirectAttributes redirect) {
		rest.delete("http://localhost:8080/ktxService/delete/{maDichVu}", maDichVu);
		redirect.addFlashAttribute("successMessage", "Deleted successfully !");
		return "redirect:/serviceHome";
	}
}

