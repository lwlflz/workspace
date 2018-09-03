package com.build.cloud.test;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;

@Controller
@RequestMapping("/myApp")
public class AppController extends AbstractController {
	
	@GetMapping("/test")
	public String list() {
			
		TestUser tu = new TestUser();
		tu.setName("liuwei");
		tu.setAge(20);
		
		return "test";
	}
}
