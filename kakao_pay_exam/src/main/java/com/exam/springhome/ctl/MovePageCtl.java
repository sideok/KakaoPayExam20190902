package com.exam.springhome.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 화면이동 Controller
 * @author SIDeok
 */

@Controller
public class MovePageCtl {
	@RequestMapping("/") 
	public String index() { 
		return "index"; 
	} 
	
	@RequestMapping("/testPage") 
	public String testPage() { 
		return "testPage"; 
	} 
}
