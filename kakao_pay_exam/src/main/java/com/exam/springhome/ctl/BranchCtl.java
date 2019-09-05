package com.exam.springhome.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 부점정보 Controller
 * @author SIDeok
 */

@RestController
@RequestMapping("/branch")
public class BranchCtl {
	@GetMapping("/orderByAmt")
	public String index() { 
		return "index"; 
	} 
	
	@GetMapping("/amt/{id}") 
	public String testPage() { 
		return "testPage"; 
	} 
}
