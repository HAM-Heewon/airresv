package kr.co.air.dtos;

import lombok.Data;

@Data
public class AirCodeDto {
	private Long AIR_IDX;
	private String FLIGHT_CD,AIRPORT_CD,AIRLINE_NM,FLIGHT_NM,USE_STATUS;
}
