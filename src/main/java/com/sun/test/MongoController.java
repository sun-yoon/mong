package com.sun.test;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.domain.Person;
import com.sun.service.MongoService;
@Controller
public class MongoController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private MongoService service;
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public void find(Model model) throws Exception{
		List<Person> data = service.find();
		model.addAttribute("data",data);
		
	}
}
