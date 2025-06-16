package kr.co.air.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.air.dtos.UsersDto;

@Mapper
public interface UserMapper {
	
	UsersDto findByUsername(String adminId);
    
	UsersDto findusers(String adminId);
	List<UsersDto> findallusers();
	
	void updateAdminAgree(@Param("adIdx") Long adIdx, @Param("adminAgree") String adminAgree);
}
