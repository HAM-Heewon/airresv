package kr.co.air.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.air.dtos.AirCodeDto;
import kr.co.air.dtos.SchedDto;
import kr.co.air.dtos.SeatDto;
import kr.co.air.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductMapper pmapper;
	
	@Override
	public List<SchedDto> getAllProduct(String stype, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("searchType", stype);
		params.put("searchKeyword", keyword);
		
		List<SchedDto> datas = pmapper.findAllProduct(params);

		return datas;
	}
	
	//항공편 등록
	@Override
	@Transactional
	public void registerSchedule(SchedDto dto) {
		pmapper.insertFlightSchedule(dto);
		
		List<SeatDto> sType = dto.getSeatTypes();
		if(sType != null && !sType.isEmpty()) {
			sType.forEach(seat ->
			seat.setSchedIdx(dto.getScheduleIdx().intValue()));
			pmapper.insertFlightSeatTypes(sType);
		}
	}
	
	//공항 코드 가져오기
	@Override
	public List<String> getAirportcode() {
		return pmapper.findDistinctAirportCodes();
	}
	
	//공항에 등록된 항공사 가져오기
	@Override
	public List<String> getlinesByAirport(String airportCode) {
		return pmapper.findAirlinesByAirport(airportCode);
	}
	
	//항공편 가져오기
	@Override
	public List<AirCodeDto> getflightName(String airportCode, String airlineName) {
		return pmapper.findFlightInfo(airportCode, airlineName);
	}
	
	
	//항공편 삭제
	@Override
	public void deleteProduct(List<Long> scheduleIdx) {
		if(scheduleIdx != null && !scheduleIdx.isEmpty()) {
			pmapper.deleteitemById(scheduleIdx);;
		}
		
	}
	
}
