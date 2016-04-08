package com.sun.test;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.domain.TestVO;
import com.sun.service.Rservice;



@Controller

public class RserveController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Inject
	private Rservice r;
	
	@RequestMapping(value="/ff")
	public void ff(Model model) throws Exception{
		List<TestVO> data = r.ff();
		System.out.println(data);
		
		
	}
	
	

	 
	
	
	@RequestMapping(value = "/rserve", method = RequestMethod.GET)
	public void rserve(Model model) {
		RConnection connection = null;
		
		 try {
            
             connection = new RConnection();
 
             String vector = "c(1,2,3,4)";
             connection.eval("meanVal=mean(" + vector + ")");
             String mean = connection.eval("meanVal").asString();
             System.out.println("The mean of given vector is=" + mean);
             String data = "The mean of given vector is=" + mean;
             model.addAttribute("data", data);
         } catch (RserveException e) {
             e.printStackTrace();
         } catch (REXPMismatchException e) {
             e.printStackTrace();
         }finally{
             connection.close();
         }
     }
	
	@RequestMapping(value="/lib",  method = RequestMethod.GET)
	public void lib(Model model) throws REXPMismatchException{
		RConnection connection = null;
		 
		try {
	            
             connection = new RConnection();
            
             String arules = "arules";
             connection.eval("library(\"arules\")");
             System.out.println("1");
           
             connection.eval("df <- read.csv(\"D:/R/data/sample-data-1.csv\")");
             System.out.println("2");
             connection.eval("str(df)");
             System.out.println("3");
             connection.eval("table(df$id)");
             System.out.println("4");
             connection.eval("mong.list <- split(df$names, df$id)");
             System.out.println("5");
             connection.eval("mong.transaction <- as(mong.list, \"transactions\")");
             System.out.println("6");
             connection.eval("rules = apriori(mong.transaction)");
             System.out.println("8");
             connection.eval("summary(rules)");
             System.out.println("9");
             connection.eval("rule.list <- as.data.frame(inspect(rules))");
             System.out.println("10");
             connection.eval("rule.list <- rule.list[order(rule.list$lift,decreasing = TRUE),]");
             System.out.println("11");
             RList q = connection.eval("rule.list").asList();
             System.out.println(q.values().toString());
             
             
//             String[] q = connection.eval("table(df$id)").asStrings();
//             System.out.println(Arrays.toString(q));
             
//             for(int i=0;i<q.length;i++) {
//            	 for(int j=0;j<q[i].length;j++) {
//            		 System.out.print(q[i][j]+" ");
//            	 }
//            	 System.out.println();
//             }
             
//             model.addAttribute("q", q);
         } catch (RserveException e) {
             e.printStackTrace();
         }finally{
             connection.close();
         }
	}
}
