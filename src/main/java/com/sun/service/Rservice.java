package com.sun.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import com.sun.domain.BoardVO;
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
            connection.eval("rules = apriori(rioter.transaction, parameter = list(support=0.01, confidence=0.2))");
            connection.eval("rule.list <- as.data.frame(inspect(rules))");
            connection.eval("write.csv(rule.list, \"d:/rule.csv\")");            
            connection.eval("song <- subset.data.frame(rule.list, select = c(lhs,rhs,lift))");
            connection.eval("song <- subset(song, lhs!=\"{}\")");
            connection.eval("song<-song[order(song[,\"lhs\"]),]");
            connection.eval("write.csv(song,\"d:/lift.txt\")");
            connection.eval("library(recommenderlab)");
            connection.eval("song <- as(song,\"realRatingMatrix\")");
            connection.eval("split.matrix <- sample(nrow(song),replace=FALSE)");
            connection.eval("training <- song[split.matrix,]");
            connection.eval("test <- song[-split.matrix,]");
            connection.eval("r <- Recommender(training, method= \"POPULAR\")");
            connection.eval("recom <- predict(r, song[1:68], n=5)");
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
				s= s.replace("0","");
				s= s.replace("1","");
				s= s.replace("2","");
				s= s.replace("3","");
				s= s.replace("4","");
				s= s.replace("5","");
				s= s.replace("6","");
				s= s.replace("7","");
				s= s.replace("8","");
				s= s.replace("9","");
				s= s.replace(",{","-");
				s= s.replace("}","");
				s= s.replace(",.","");
				s= s.replace(",,,","");
				
				System.out.println(s);
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
			s= s.replace("c...","");
			s= s.replace("\"","");
			
			s= s.replace("......","-");
			s= s.replace("...","");
			s= s.replace(".","");
			String pattern ="[^0-9]";
			Pattern r = Pattern.compile(pattern);

		     
		     Matcher m = r.matcher(s);
		    System.out.println(m.replaceAll("")); 
		
			s= s.replace("0","");
			s= s.replace("1","");
			s= s.replace("2","");
			s= s.replace("3","");
			s= s.replace("4","");
			s= s.replace("5","");
			s= s.replace("6","");
			s= s.replace("7","");
			s= s.replace("8","");
			s= s.replace("9","");
			
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
	
	public Map<String, String> mapping() throws Exception {
		Map<String, String> recommendMap = new HashMap<String, String>();
		String recom="";
		FileReader fr1 = null;		
		BufferedReader br1 = null;
		FileReader fr2 = null;		
		BufferedReader br2 = null;	
		
		
		try{
			fr1 = new FileReader("d:/lift(c).txt");
			br1 = new BufferedReader(fr1);
			fr2 = new FileReader("d:/recom(c).txt");
			br2 = new BufferedReader(fr2);
			
			String s1 = br1.readLine();
			String s2 = br2.readLine();
			String prestr=null;
			
			while((s1 = br1.readLine())!= null){
				
				String[] split1 = s1.split("-");
				if(split1[1].equals(prestr)) {
					recom = split1[2]+","+recom;
					recommendMap.put(split1[1], recom);
				}
				else {					
					s2 = br2.readLine();					
					String[] split2 = s2.split("-");

					recom = split1[2]+","+split2[0]+","+split2[1];					
					
					recommendMap.put(split1[1], recom);
					
				}				
				prestr = split1[1];				
			}	
		}catch(Exception e){		
			e.printStackTrace();
		}finally{			
			if(br1 != null) try{br1.close();}catch(IOException e){}
			if(fr1 != null) try{fr1.close();}catch(IOException e){}
			
			if(br2 != null) try{br2.close();}catch(IOException e){}
			if(fr2 != null) try{fr2.close();}catch(IOException e){}	
		}
		
		return recommendMap;
	}
	
	public List<String> getCategory(long memNo)throws Exception{
		List<String> categoryList = dao.getCategory(memNo);
		
		for(int i = 0; i<categoryList.size();i++){
			String cg = categoryList.get(i);
			System.out.println(cg);
			
		}
		return categoryList;
	}
	
	public List<String> matching() throws Exception {
		List<String> catelist = this.getCategory(190);
		List<String> catevalue = new ArrayList<String>();
		String totalCategory = catelist.get(0)+","+catelist.get(1)+","+catelist.get(2);
		String subCategory1 = catelist.get(0)+","+catelist.get(1);
		String subCategory2 = catelist.get(0)+","+catelist.get(2);
		String subCategory3 = catelist.get(1)+","+catelist.get(2);
		
		Map<String, String> catemap = this.mapping();
		
		if(catemap.containsKey(totalCategory)) {
			catevalue.add(catemap.get(totalCategory));
		}
		else if(catemap.containsKey(subCategory1) || catemap.containsKey(subCategory2) || catemap.containsKey(subCategory3)) {
			if(catemap.containsKey(subCategory1)) {
				catevalue.add(catemap.get(subCategory1));
			}
			if(catemap.containsKey(subCategory2)) {
				catevalue.add(catemap.get(subCategory2));
			}
			if(catemap.containsKey(subCategory3)) {
				catevalue.add(catemap.get(subCategory3));
			}
		}
		else {
			if(catemap.containsKey(catelist.get(0))) {
				catevalue.add(catemap.get(catelist.get(0)));
			}
			if(catemap.containsKey(catelist.get(1))) {
				catevalue.add(catemap.get(catelist.get(1)));
			}
			if(catemap.containsKey(catelist.get(2))) {
				catevalue.add(catemap.get(catelist.get(2)));
			}
		}
		
		return catevalue;
	}
	
	public Set<String> dividecategory() throws Exception {
		List<String> catevalue = this.matching();
		Set<String> recomset = new HashSet<String>();
		
		for(int i=0;i<catevalue.size();i++) {
			String[] split = catevalue.get(i).split(",");
			for(int j=0;j<split.length;j++) {
				recomset.add(split[j]);
			}
		}
		
		return recomset;
	}
	
	public Set<List<BoardVO>> recommend() throws Exception{		
		Set<List<BoardVO>> boardset = new HashSet<List<BoardVO>>();
		Set<String> recomset = this.dividecategory();
		Iterator<String> it = recomset.iterator();
		while(it.hasNext()) {
			String category = it.next();			
			boardset.add(dao.recommend(category));
		}		
		
		return boardset;
	}
}
