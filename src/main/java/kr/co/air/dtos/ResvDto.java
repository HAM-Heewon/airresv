package kr.co.air.dtos;

import java.sql.Date;

import lombok.Data;

@Data
public class ResvDto {
	private Long AIR_RSNO, ADULT, CHILDREN1, CHILDREN2;
	private String AIR_RSPART,AIR_DEPARTNM, AIR_ARRIVNM, AIR_SEAT, P_CODE;
	private Date  DEPART_DATE, ARRIVAL_DATE;
}
