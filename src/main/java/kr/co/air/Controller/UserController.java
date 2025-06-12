package kr.co.air.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.air.dtos.UsersDto;
import kr.co.air.mapper.AirMapper;

@Controller
@RequestMapping("/users")
public class UserController {

	private final AirMapper airmapper;
	
    @Autowired
    public UserController(AirMapper airmapper) {
        this.airmapper = airmapper;
    }
    
    @GetMapping
    public String listUsers(Model m) {
        // UserMapper를 사용하여 모든 사용자 정보를 데이터베이스에서 조회합니다.
        List<UsersDto> users = airmapper.findusers();
        
        // 조회된 사용자 목록을 Model 객체에 담아 뷰로 전달합니다.
        m.addAttribute("users", users);
        
        // "userList"라는 뷰 이름을 반환합니다.
        // Thymeleaf 설정에 따라 src/main/resources/templates/userList.html 파일을 찾게 됩니다.
        return "/userList.html";
    }
}
	