package com.exam.springhome.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exam.springhome.exception.BrNameNotFoundException;
import com.exam.springhome.svc.ProcSvc;

/**
 * 부점정보 Controller
 * @author SIDeok
 */

@RestController
@RequestMapping("/branch")
public class BranchCtl {
	
	@Autowired
	ProcSvc svcProc;
	
	/**
	 * 입력년도의 관리점의 거래금액 합계를 내림차순으로 정렬하여 반환  하기위한 Controller
	 * @param year
	 * @return 
	 */
	@GetMapping("/order-by-samt/{year}")
	public List<Map<String, Object>> getOrderBySamt(@PathVariable String year) { 
		return svcProc.getAmountByYearByBranch(year); 
	} 
	/**
	 * 모든 년도의 관리점의 거래금액 합계를 내림차순으로 정렬하여 반환  하기위한 Controller
	 * @return 
	 */
	@GetMapping("/order-by-samt")
	public List<Map<String, Object>> getOrderBySamt() { 
		return svcProc.getAmountByYearByBranch(null); 
	} 
	
	/**
	 * 입력한 관리점명에 해당하는 거래금액의 합계를 반환 하기위한 Controller
	 * - 없는 관리점이 입력되거나 관리점이 입력되지 않으면 Exception 처리
	 * @param map
	 * @return
	 * @throws BrNameNotFoundException
	 */
	@PostMapping("/samt-by-branch") 
	public Map<String, Object> samtByBranch(@RequestBody Map<String, Object> map) throws BrNameNotFoundException { 
		
		// 입력값이 없을경우 Exception 처리
		if(map.get("brName") == null || map.get("brName").toString().isEmpty()) {
			throw new BrNameNotFoundException("br code not found error");
		}
		
		// 서비스 호출
		Map<String, Object> rtnMap = svcProc.getSumOfBranchAmt(map.get("brName").toString());
		
		// 존재하지 않는 관리점일 경우 Exception 처리
		if(rtnMap.size() == 0) {
			throw new BrNameNotFoundException("br code not found error");
		}
		return rtnMap;
		
	} 
	
	/**
	 * BrNameNotFoundException 이 발생하였을 경우 처리 할 ResponseStatus 및 
	 * 메세지 처리를 위한 Contoller
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BrNameNotFoundException.class)
	public Map<String, Object> samtByBranchError(BrNameNotFoundException e) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("메세지", e.getMessage());
		rtnMap.put("code", "404");
		return rtnMap;
	}
	
}
