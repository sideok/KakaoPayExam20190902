package com.exam.springhome.ctl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.springhome.svc.ProcSvc;

/**
 * 고객정보 Controller
 * @author SIDeok
 */

@RestController
@RequestMapping("/cust")
public class CustCtl {
	
	@Autowired
	ProcSvc svcProc;
	
	/**
	 * 입력년도중 거래금액의 합계가 가장 큰 고객을 출력 하기위한 Controller
	 * @param year
	 * @return
	 */
	@GetMapping("/get-max-amt/{year}")
	public List<Map<String, Object>> getMaxAmt(@PathVariable String year) { 
		return svcProc.getMaxSumAmoutByYear(year); 
	} 
	/**
	 * 전체 거래년도 중 거래금액의 합계가 가장 큰 고객을 출력 하기위한 Controller
	 * @return
	 */
	@GetMapping("/get-max-amt")
	public List<Map<String, Object>> getMaxAmt() { 
		return svcProc.getMaxSumAmoutByYear(null); 
	} 
	
	/**
	 * 입력년도에서 거래가 없었던 고객을 추출 하기위한 Controller
	 * @param year
	 * @return
	 */
	@GetMapping("/get-no-hist-cust/{year}") 
	public List<Map<String, Object>> testPage(@PathVariable String year) { 
		return svcProc.getAccountNoHistory(year);
	} 
	/**
	 * 모든 거래년도에서 거래가 없었던 고객을 추출 하기위한 Controller
	 * @return
	 */
	@GetMapping("/get-no-hist-cust") 
	public List<Map<String, Object>> testPage() { 
		return svcProc.getAccountNoHistory(null);
	} 
}
