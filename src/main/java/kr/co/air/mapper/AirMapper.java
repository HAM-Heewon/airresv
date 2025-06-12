package kr.co.air.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.air.dtos.UsersDto;

@Mapper
public interface AirMapper {
    
	List<UsersDto> findusers();
}
