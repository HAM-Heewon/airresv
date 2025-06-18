package kr.co.air.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.air.dtos.DatalistDto;
import kr.co.air.mapper.NoticeMapper;
import kr.co.air.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeMapper mapper;
	private final UserMapper usermapper;
	
	//공지사항 전체
	public List<DatalistDto> getAllNotice(){
		return mapper.findAllNotice();
	}
	
	//공지사항 입력
    @Transactional
    public void saveNotice(DatalistDto dto, Authentication auth) {
        
    	String writerName = "관리자";

        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            writerName = userDetails.getUsername();
        }
        dto.setEname(writerName);

        Long empno = usermapper.findAdIdx(writerName);
        if (empno != null) {
            dto.setEmpno(empno);
        }
        
        dto.setFileName(null);
        dto.setFileSavename(null);
        dto.setFilePath(null);
        dto.setFileSize(0L);

        // 4. empno가 설정된 DTO를 DB에 저장
        mapper.insertNotice(dto);
    }
	
	//공지 상세
    @Transactional 
    public DatalistDto getNoticeById(int eno) {
        // 1. 조회수 1 증가
        mapper.incrementView(eno);
        // 2. 게시글 정보 조회 후 반환
        return mapper.findById(eno);
    }
    
    //공지 수정을 위한 상세 데이터 조회
    public DatalistDto getNoticeForUpdate(int eno) {
        return mapper.findById(eno);
    }
    
    //공지 수정
    @Transactional
    public void updateNotice(DatalistDto dto) {
        mapper.updateNotice(dto);
    }
    
    //공지 삭제
    @Transactional
    public void deleteNoticesByIds(List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            mapper.deleteByIds(ids);
        }
    }
}
