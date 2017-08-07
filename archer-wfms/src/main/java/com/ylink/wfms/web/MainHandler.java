package com.ylink.wfms.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainHandler {
	@RequestMapping("goMain.action")
	public String goMain(){
		return "workflow/pages/wfms-index";
	}
	
	@RequestMapping("goError.action")
	public String goError(){
		return "common/error";
	}

}
