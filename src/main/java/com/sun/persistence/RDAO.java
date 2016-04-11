package com.sun.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
	
	public List<TestVO> getCategory() throws Exception{
		List<TestVO> getCategory = session.selectList(namespace+".getCategory");
		return getCategory;
	}
}
