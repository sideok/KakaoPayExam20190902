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
	
	@GetMapping("/order-by-samt/{year}")
	public List<Map<String, Object>> getOrderBySamt(@PathVariable String year) { 
		return svcProc.getAmountByYearByBranch(year); 
	} 
	@GetMapping("/order-by-samt")
	public List<Map<String, Object>> getOrderBySamt() { 
		return svcProc.getAmountByYearByBranch(null); 
	} 
	
	@PostMapping("/samt-by-branch") 
	public Map<String, Object> samtByBranch(@RequestBody Map<String, Object> map) throws BrNameNotFoundException { 
		if(map.get("brName") == null || map.get("brName").toString().isEmpty()) {
			throw new BrNameNotFoundException("br code not found error");
		}
		
		Map<String, Object> rtnMap = svcProc.getSumOfBranchAmt(map.get("brName").toString());
		if(rtnMap.size() == 0) {
			throw new BrNameNotFoundException("br code not found error");
		}
		return rtnMap;
		
	} 
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BrNameNotFoundException.class)
	public Map<String, Object> samtByBranchError(BrNameNotFoundException e) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("메세지", e.getMessage());
		rtnMap.put("code", "404");
		return rtnMap;
	}
	
}
