package com.sun.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import javax.inject.Inject;

import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import com.sun.domain.TestVO;
import com.sun.persistence.RDAO;

@Service
public class Rservice {

	@Inject
	private RDAO dao;
	
	public List<TestVO> ff() throws Exception{
		List<TestVO> data = dao.ff();
		
		System.out.println(data.get(1).getCategory());
		
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter("d:/category.txt", false);
			bw = new BufferedWriter(fw);
			
			bw.write("user,category");
			bw.newLine();
			for(int i=0;i<data.size();i++) {
				bw.write(data.get(i).getMem_snum()+","+data.get(i).getCategory());
				bw.newLine();
			}
			
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public void rconnect() throws Exception {
		RConnection connection = null;
		 
		try {	            
            connection = new RConnection();
            
            connection.eval("library(arules)");
            connection.eval("dbdata <- read.csv(\"d:/category.txt\")");
            connection.eval("rioter.list <- split(dbdata$category, dbdata$user)");
            connection.eval("rioter.list <- as(rioter.list, \"transactions\")");
            connection.eval("rules = apriori(rioter.transaction)");
            connection.eval("rule.list <- as.data.frame(inspect(rules))");
            connection.eval("write.csv(rule.list, \"d:/rule.csv\")");            
             
         } catch (RserveException e) {
             e.printStackTrace();
         }finally{
             connection.close();
         }
	}
	
	
	
}
