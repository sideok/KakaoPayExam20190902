package com.exam.springhome.ctl;

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
	public Map[] getMaxAmt(@PathVariable String year) { 
		return svcProc.getMaxSumAmoutByYear(year); 
	} 
	
	@GetMapping("/noTranCust") 
	public String testPage() { 
		return "testPage"; 
	} 
}
