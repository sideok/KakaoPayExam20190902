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
	
	@GetMapping("/get-max-amt/{year}")
	public List<Map<String, Object>> getMaxAmt(@PathVariable String year) { 
		return svcProc.getMaxSumAmoutByYear(year); 
	} 
	@GetMapping("/get-max-amt")
	public List<Map<String, Object>> getMaxAmt() { 
		return svcProc.getMaxSumAmoutByYear(null); 
	} 
	
	@GetMapping("/get-no-hist-cust/{year}") 
	public List<Map<String, Object>> testPage(@PathVariable String year) { 
		return svcProc.getAccountNoHistory(year);
	} 
	@GetMapping("/get-no-hist-cust") 
	public List<Map<String, Object>> testPage() { 
		return svcProc.getAccountNoHistory(null);
	} 
}
