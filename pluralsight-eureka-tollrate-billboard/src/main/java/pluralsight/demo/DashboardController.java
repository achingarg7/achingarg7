package pluralsight.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@Controller
public class DashboardController {
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplate) {
		return restTemplate.build();
	}
	
	@Autowired
	public RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "defaultRate")
	@RequestMapping("/dashboard")
	public String GetTollRate(@RequestParam int stationId, Model m) {
		
//		RestTemplate rest = new RestTemplate();
		TollRate tr = restTemplate.getForObject("http://toll-service/tollrate/" + stationId, TollRate.class);
		System.out.println("stationId: " + stationId);
		m.addAttribute("rate", tr.getCurrentRate());
		return "dashboard";
	}
	
	public String defaultRate(@RequestParam int stationId, Model m) {
		System.out.println("==================FallBack Operation================================");
		m.addAttribute("rate", "NA");
		return "dashboard";
	}
}
