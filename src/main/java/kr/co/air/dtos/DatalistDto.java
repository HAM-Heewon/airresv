package kr.co.air.dtos;

import java.sql.Date;

import lombok.Data;

@Data
public class DatalistDto {
	private Long ENO, EMPNO;
	private String ENAME, ESUBJECT;
	private Date CREAT_DATE, UPDATE_DATE;
}
