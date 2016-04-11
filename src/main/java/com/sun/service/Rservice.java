package com.sun.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            connection.eval("rioter.transaction <- as(rioter.list, \"transactions\")");
            connection.eval("rules = apriori(rioter.transaction)");
            connection.eval("rule.list <- as.data.frame(inspect(rules))");
            connection.eval("write.csv(rule.list, \"d:/rule.csv\")");            
            connection.eval("song <- subset.data.frame(rule.list, select = c(lhs,rhs,lift))");
            connection.eval("song<-song[order(song[,\"lhs\"]),]");
            connection.eval("write.csv(song,\"d:/lift.txt\")");
            connection.eval("library(recommenderlab)");
            connection.eval("song <- as(song,\"realRatingMatrix\")");
            connection.eval("split.matrix <- sample(nrow(song),replace=FALSE)");
            connection.eval("training <- song[split.matrix,]");
            connection.eval("test <- song[-split.matrix,]");
            connection.eval("r <- Recommender(training, method= \"POPULAR\")");
            connection.eval("recom <- predict(r, song[1:4])");
            connection.eval("mong <- as(recom, \"list\")");
            connection.eval("write.csv(mong, \"d:/recom.txt\")");
         } catch (RserveException e) {
             e.printStackTrace();
         }finally{
             connection.close();
         }
	}
	
	public void readLift() throws Exception{
		FileReader fr = null;
		FileWriter fw = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			fr = new FileReader("d:/lift.txt");
			br = new BufferedReader(fr);
			
			fw = new FileWriter("d:/lift(c).txt");
			bw = new BufferedWriter(fw);
			
			String s = null;
			while((s= br.readLine())!=null){
				s= s.replace("lhs","");
				s= s.replace("rhs","");
				s= s.replace("lift","");
//				s= s.replace(",","");
				s= s.replace("\"","");
			
				bw.write(s);
				bw.newLine();
				
			}
			
		
		}catch(Exception e){
		
			e.printStackTrace();
	
		}finally{
			
			if(br != null) try{br.close();}catch(IOException e){}
			if(fr != null) try{fr.close();}catch(IOException e){}
			
			if(bw != null) try{bw.close();}catch(IOException e){}
			if(fw != null) try{fw.close();}catch(IOException e){}
			
		}
	
	}
	
	
	public void readRecom() throws Exception{
		FileReader fr = null;
		FileWriter fw = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			fr = new FileReader("d:/recom.txt");
			br = new BufferedReader(fr);
			
			fw = new FileWriter("d:/recom(c).txt");
			bw = new BufferedWriter(fw);
			
			String s = null;
			s= br.readLine();
			s= s.replace("c","");
			s= s.replace("\"","");
			
			String [] result = s.split(",");
			for(int i =0; i < result.length; i++){
				bw.write(result[i]);
				bw.newLine();
				System.out.println(result[i]);
			}
			
		
		}catch(Exception e){
		
			e.printStackTrace();
	
		}finally{
			
			if(br != null) try{br.close();}catch(IOException e){}
			if(fr != null) try{fr.close();}catch(IOException e){}
			
			if(bw != null) try{bw.close();}catch(IOException e){}
			if(fw != null) try{fw.close();}catch(IOException e){}
			
		}
	
	}
	
//	public void analysis()throws Exception{
//		
//		FileReader fr1 = new FileReader("d:/lift(c).txt");
//		BufferedReader br1 = new BufferedReader(fr1);
//		String s = null;
//		while((s= br1.readLine())!=null){
//			String [] result = s.split(",");
//			for(int i =0; i < result.length; i++){
//				
//				System.out.println(result[i]);
//			}
//		}
//		
//		
//		
//		FileReader fr2 = new FileReader("d:/lift(c).txt");
//		BufferedReader br2 = new BufferedReader(fr2);
//		
//		
//		Map<String, ArrayList<String>> analysis = new HashMap<String,ArrayList<String>>();
//		
//		
//		
//	}
//	
}
