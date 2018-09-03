package com.build.cloud.core.base.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.id.IdGenerator;

@RestController
@RequestMapping("/idGenerator")
public class IdGeneratorController {
	
	@Autowired
	private IdGenerator idGenerator;
	
	@GetMapping("/v1/id")
	public Result getId(@RequestParam String menu){
		String id = idGenerator.getNewMax(menu, 3, "", "1007146214325501953","gc");
		return Result.ok().put("result", id);
	}

}
