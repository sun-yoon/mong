package com.sun.test;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.service.Rservice;



@Controller

public class RserveController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private Rservice r;
	
	@RequestMapping(value="/ff")
	public void ff(Model model) throws Exception{
		r.ff();		
		r.rconnect();
		r.readLift();
		r.readRecom();
		r.mapping();
		System.out.println(r.matching());
		System.out.println(r.dividecategory());
		
		model.addAttribute("boardlist", r.getrecomBoard());
	} 	
	
	@RequestMapping(value="/getCategory")
	public void getCategory(Model model) throws Exception{
		List<String> test = r.matching();
		System.out.println(test);
	}
}
