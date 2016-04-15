package com.sun.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sun.domain.BoardVO;
import com.sun.domain.TestVO;

@Repository
public class RDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace="rserve";
	
	public List<TestVO>ff(){
		List<TestVO> data = session.selectList(namespace+".ff");
		return data;
	}
	
	public List<String> getCategory(long memNo) throws Exception{
		List<String> getCategory = session.selectList(namespace+".getCategory", memNo);
		return getCategory;
	}
	
	public List<BoardVO> recommend(String category) throws Exception {
		List<BoardVO> recomboard = session.selectList(namespace+".recommend", category);
		return recomboard;
	}

	public void createScoreTable(long mem_snum) {
		session.insert(namespace+".createscore", mem_snum);		
	}
	
	public List<String> selectqueryboard(String mem_id) {
		List<String> queryboard = session.selectList(namespace+".selectqueryboard", mem_id);
		return queryboard;
	}
}
