package com.sun.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sun.persistence.RDAO;

@Service
public class ScoreService {

	@Inject
	private RDAO dao;
	
	public void createScoreTable(long mem_snum) {
		dao.createScoreTable(mem_snum);
	}
	
	public void calScore(String mem_id) {
		List<String> queryboard = dao.selectqueryboard(mem_id);
	}
}
