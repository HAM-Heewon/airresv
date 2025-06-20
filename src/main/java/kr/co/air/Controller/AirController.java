package kr.co.air.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AirController {
    
    
	
    @GetMapping("/")
    public String home() {
    	return "/Login";
    }

    
    @GetMapping("/ticketing_list")
    public String ticketing_list() {
    	return "ticketing_list";
    }
    @GetMapping("/seat_list")
    public String seat_list() {
    	return "seat_list";
    }
    @GetMapping("/faq_list")
    public String faq_list() {
    	return "faq_list";
    }
    @GetMapping("/faq_write")
    public String faq_write() {
    	return "faq_write";
    }
    @GetMapping("/faq_update")
    public String faq_update() {
    	return "faq_update";
    }
    @GetMapping("index")
    public String index() {
    	return "index";
    }

}
