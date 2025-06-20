package kr.co.air.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.air.Service.ProductService;
import kr.co.air.dtos.AirCodeDto;
import kr.co.air.dtos.SchedDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService pservice;
	
    @GetMapping("/product_list")
    public String product_list(Model m,
    		@RequestParam(value = "searchType", required = false) String stype,
    		@RequestParam(value = "searchKeyword", required = false) String keyword) {
    	
    	List<SchedDto> alldata = pservice.getAllProduct(stype,keyword);
    	m.addAttribute("alldata",alldata);
    	return "product_list";
    }
    
    @GetMapping("/product_new")
    public String product_new(Model m) {
    	List<String> portCode = pservice.getAirportcode();
        m.addAttribute("portCode", portCode);
        m.addAttribute("frm", new SchedDto());
    	return "product_new";
    }
    
    @PostMapping("/product/add")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody SchedDto frm) { 
    	try {
    		pservice.registerSchedule(frm);
    		return ResponseEntity.ok("항공 스케줄이 성공적으로 등록되었습니다.");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("등록 중 오류가 발생했습니다.");
		}
    }
    
    @GetMapping("/product/airline")
    @ResponseBody
    public List<String> getlinesByAirport(@RequestParam("airportCode") String airportCode){
    	return pservice.getlinesByAirport(airportCode);
    }
    
    @GetMapping("/product/flightname")
    @ResponseBody
    public List<AirCodeDto> getflightName(@RequestParam("airportCode") String airportCode, @RequestParam("airlineName") String airlineName){
    	return pservice.getflightName(airportCode, airlineName);
    }
    
    @DeleteMapping("/product/delete")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestBody List<Long> scheduleIdx){
    	try {
    		pservice.deleteProduct(scheduleIdx);
    		return ResponseEntity.ok(scheduleIdx.size() + "개의 항공 편이 삭제되었습니다.");
    	}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류 발생");
		}
    }
    
}
