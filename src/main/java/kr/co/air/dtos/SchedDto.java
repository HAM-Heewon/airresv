package kr.co.air.dtos;

import lombok.Data;

@Data
public class SchedDto {
	private Long SCHEDULE_IDX, TOTAL_SEATS;
	private String AIRPORT_CD, AIRLINE_NM, FLIGHT_CD, FLIGHT_NM, DEPARTURE_NM, ARRIVAL_NM, SEAT_TYPE, FLIGHT_STATUS;
}
