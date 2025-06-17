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
    
    @GetMapping("/notice")
    public String notice() {
    	return "notice_list";
    }
    
    @GetMapping("/notice_write")
    public String notice_write() {
    	return "notice_write";
    }
    
    @GetMapping("/notice_view")
    public String notice_view() {
    	return "notice_view";
    }
    
    @GetMapping("/notice_update")
    public String notice_update() {
    	return "notice_update";
    }
    
    @GetMapping("/product_list")
    public String product_list() {
    	return "product_list";
    }
    
    @GetMapping("/product_new")
    public String product_new() {
    	return "product_new";
    }

    @GetMapping("/air_newcode")
    public String air_newcode() {
    	return "air_newcode";
    }

    @GetMapping("/code_list")
    public String code_list() {
    	return "code_list";
    }
    @GetMapping("/code_update")
    public String code_update() {
    	return "code_update";
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
