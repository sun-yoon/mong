package com.sun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sun.domain.TestVO;
import com.sun.persistence.RDAO;

@Service
public class Rservice {

	@Inject
	private RDAO dao;
	
	public List<TestVO> ff() throws Exception{
		List<TestVO> data = dao.ff();
		return data;
	}
	
	
	
}
