package kr.co.air.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	

    private final JdbcTemplate jdbc;
    
    @Autowired
    public TestController(@Qualifier("mysqlJdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
	
    @GetMapping("/")
    public String home() {
    	return "/Login.html";
    }
    
    @GetMapping("/siteinfo")
    public String siteinfo() {
    	return "siteinfo.html";
    }
    
    @GetMapping("/notice")
    public String notice() {
    	return "notice_list.html";
    }
    
    @GetMapping("/notice_write")
    public String notice_write() {
    	return "notice_write.html";
    }
    
    @GetMapping("/notice_view")
    public String notice_view() {
    	return "notice_view.html";
    }
    
    @GetMapping("/notice_update")
    public String notice_update() {
    	return "notice_update.html";
    }
    
    @GetMapping("/product_list")
    public String product_list() {
    	return "product_list.html";
    }
    
    @GetMapping("/product_new")
    public String product_new() {
    	return "product_new.html";
    }

    @GetMapping("/air_newcode")
    public String air_newcode() {
    	return "air_newcode.html";
    }

    @GetMapping("/code_list")
    public String code_list() {
    	return "code_list.html";
    }
    @GetMapping("/code_update")
    public String code_update() {
    	return "code_update.html";
    }
    
    @GetMapping("/ticketing_list")
    public String ticketing_list() {
    	return "ticketing_list.html";
    }
    @GetMapping("/seat_list")
    public String seat_list() {
    	return "seat_list.html";
    }
    @GetMapping("/faq_list")
    public String faq_list() {
    	return "faq_list.html";
    }
    @GetMapping("/faq_write")
    public String faq_write() {
    	return "faq_write.html";
    }
    @GetMapping("/faq_update")
    public String faq_update() {
    	return "faq_update.html";
    }
    
    @GetMapping("/login")
    public String login() {
        return "Login.html"; // / 요청 시 /users 경로로 리다이렉트
    }
    
    @GetMapping("index")
    public String index() {
    	return "index.html";
    }
    
    @GetMapping("add_master")
    public String add_master() {
    	return "add_master.html";
    }
    
    @GetMapping("admin_list")
    public String adminList(Model m) {
    	List<Map<String, Object>> adminList = new ArrayList<>();
        
        Map<String, Object> admin1 = new HashMap<>();
        admin1.put("id", 1);
        admin1.put("name", "한석봉");
        admin1.put("userId", "hansbong");
        admin1.put("phoneNumber", "01012345678");
        admin1.put("email", "hansbong@hanmail.net");
        admin1.put("department", "디자인팀");
        admin1.put("position", "주임");
        admin1.put("joinDate", "2022-08-12");
        
        Map<String, Object> admin2 = new HashMap<>();
        admin2.put("id", 2);
        admin2.put("name", "김철수");
        admin2.put("userId", "kimcs");
        admin2.put("phoneNumber", "01098765432");
        admin2.put("email", "kimcs@example.com");
        admin2.put("department", "개발팀");
        admin2.put("position", "대리");
        admin2.put("joinDate", "2023-01-15");
        
        adminList.add(admin1);
        adminList.add(admin2);
        
        m.addAttribute("adminList", adminList);
    	return "admin_list.html";
    }
    
    @GetMapping("/check-db")
    @ResponseBody
    public String checkDbConnection() {
        try {
            String result = jdbc.queryForObject("SELECT NOW()", String.class);
            return "Database connection successful! Current time: " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
        }
    }
}
