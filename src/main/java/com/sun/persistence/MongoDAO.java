package com.sun.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.sun.domain.Person;

@Repository
public class MongoDAO {
	 @Autowired
	  private MongoTemplate mongoTemplate;
	 
	 
	    public List<Person> find()throws Exception{
	    	BasicQuery query1 = new BasicQuery("{}");
	    	List<Person> data = mongoTemplate .find(query1,Person.class, "newtest");
	        System.out.println(data);
	        return data;
	        
	    }    
	
}
