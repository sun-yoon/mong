package com.sun.service;

import java.util.List;

import com.sun.domain.Person;

public interface MongoService {
	
	public List<Person> find() throws Exception;
}
