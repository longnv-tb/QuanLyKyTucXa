package ktx.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import ktx.Service;

public class SelectServiceController {
	private RestTemplate rest = new RestTemplate();
	
	@RequestMapping("/selectService")
	public String home(Model model) {
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/ktxService/services", Service[].class));
		model.addAttribute("services", services);
		return "selectService";
	}
}
