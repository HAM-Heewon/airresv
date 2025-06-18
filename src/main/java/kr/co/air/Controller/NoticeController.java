package kr.co.air.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.air.Service.NoticeService;
import kr.co.air.dtos.DatalistDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
    //notice 전체 출력
    @GetMapping("/notice")
    public String notice(Model m) {
    	
    	List<DatalistDto> noticeList = service.getAllNotice();
    	
    	m.addAttribute("noticeList",noticeList);
    	
    	return "notice_list";
    }
    
    //공지사항 입력
    @GetMapping("/notice_write")
    public String notice_write(Model m, Authentication auth) {
    	
    	DatalistDto dto = new DatalistDto();
    	/*
    	if (auth != null) {

            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                dto.setEname(((UserDetails) principal).getUsername());
            } else {
                dto.setEname(principal.toString());
            }
        }*/          
            dto.setEname("관리자");

         
    	m.addAttribute("newNotice",dto);
    	
    	return "notice_write";
    }
    @PostMapping("/notice_save")
    public String saveNoticeProcess(DatalistDto dto, Authentication auth, RedirectAttributes redir) {
        try {
            service.saveNotice(dto, auth);
            redir.addFlashAttribute("successMessage", "새로운 공지사항이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            redir.addFlashAttribute("errorMessage", "등록 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return "redirect:/notice";
    }
    
    //공지사항 상세 페이지
    @GetMapping("/notice/{eno}")
    public String noticeDetailPage(@PathVariable("eno") int eno, Model model) {
    	DatalistDto notice = service.getNoticeById(eno);
        
        if (notice == null) {
            return "redirect:/notice";
        }
        
        model.addAttribute("notice", notice);
        return "notice_view";
    }
    
    //공지사항 수정-기존 데이터 불러오기
    @GetMapping("/notice_update/{eno}")
    public String noticeUpdatePage(@PathVariable("eno") int eno, Model model) {
    	DatalistDto notice = service.getNoticeForUpdate(eno);
        model.addAttribute("notice", notice);
        return "notice_update";
    }
    
    //공지사항 수정
    @PostMapping("/notice_update")
    public String noticeUpdateProcess(DatalistDto dto, RedirectAttributes redir) {
        try {
            service.updateNotice(dto);
            redir.addFlashAttribute("successMessage", "공지사항이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
        	logger.error("사이트 정보 수정 실패: {}", e.getMessage(), e);
            redir.addFlashAttribute("errorMessage", "수정 중 오류가 발생했습니다.");
        }
        return "redirect:/notice" + dto.getEno(); 
    }
    
    //공지사항 단일 삭제
    @DeleteMapping("/notice/delete/{eno}")
    @ResponseBody
    public ResponseEntity<String> deleteNotice(@PathVariable("eno") int eno) {
        try {
            // 단일 ID를 리스트에 담아 기존 서비스 메서드 재활용
            service.deleteNoticesByIds(List.of(eno));
            // 삭제 성공 시 HTTP 200 OK와 함께 성공 메시지를 응답 본문에 포함
            return ResponseEntity.ok(eno + "번 공지사항이 삭제되었습니다."); // 변경된 부분
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("공지사항 삭제 중 오류가 발생했습니다.");
        }
    }
    
    
    //공지사항 삭제
    @DeleteMapping("/notice/delete")
    @ResponseBody
    public ResponseEntity<String> deleteSelectedNotices(@RequestBody List<Integer> idsToDelete) {
        try {
            if (idsToDelete == null || idsToDelete.isEmpty()) {
                return ResponseEntity.badRequest().body("삭제할 항목을 선택해주세요.");
            }
            service.deleteNoticesByIds(idsToDelete);
            return ResponseEntity.ok(idsToDelete.size() + "개의 공지사항이 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
        }
    }
    


}
