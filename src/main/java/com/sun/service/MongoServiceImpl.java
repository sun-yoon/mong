package com.sun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sun.domain.Person;
import com.sun.persistence.MongoDAO;

@Service
public class MongoServiceImpl implements MongoService {

	@Inject
	private MongoDAO dao;	
	@Override
	public List<Person> find() throws Exception {
		List<Person> data = dao.find(); 
	      
	      return data;
	}

}
