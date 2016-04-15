package com.sun.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sun.domain.BoardVO;
import com.sun.domain.ScoreVO;
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

	public void createScoreTable(long mem_snum) throws Exception {
		session.insert(namespace+".createscore", mem_snum);		
	}
	
	public List<String> selectqueryboard(String mem_id) throws Exception {
		List<String> queryboard = session.selectList(namespace+".selectCategoryByQueryBoard", mem_id);
		return queryboard;
	}
	
	public List<String> selectlikelist(String mem_id) throws Exception {
		List<String> queryboard = session.selectList(namespace+".selectCategoryByLikeList", mem_id);
		return queryboard;
	}
	
	public List<String> selectcommentlist(String mem_id) throws Exception {
		List<String> queryboard = session.selectList(namespace+".selectCategoryByBoardComment", mem_id);
		return queryboard;
	}

	public void updatescore(long mem_snum, ScoreVO score) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_snum", mem_snum);
		map.put("score", score);
		session.update(namespace+".updatescore", map);		
	}

	public List<String> selectScore(long mem_snum) throws Exception {
		List<String> categoryRank = session.selectList(namespace+".selectscore", mem_snum);
		return categoryRank;
	}

	public void createUserCategory(long mem_snum, String defaultCategory) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_snum", mem_snum);
		map.put("default", defaultCategory);
		session.insert(namespace+".createUserCategory", map);		
	}
	
	public void deleteUserCategory(long mem_snum) throws Exception {
		session.delete(namespace+".deleteUserCategory", mem_snum);
	}
	
	public void updateUserCategory(long mem_snum, String favorCategory) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_snum", mem_snum);
		map.put("favor", favorCategory);		
		session.insert(namespace+".updateUserCategory", map);
	}
}
