package com.sun.test;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPList;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class RserveController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
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
            
             connection.eval("library(\"arules\")");
             System.out.println("1");
           
             connection.eval("df <- read.csv(\"D:/R/data/sample-data-1.csv\")");
             System.out.println("2");
             connection.eval("str(df)");
             System.out.println("3");
             double[] q = connection.eval("table(df$id)").asDoubles();
             System.out.println(Arrays.toString(q));
             
//             for(int i=0;i<q.length;i++) {
//            	 for(int j=0;j<q[i].length;j++) {
//            		 System.out.print(q[i][j]+" ");
//            	 }
//            	 System.out.println();
//             }
             
             model.addAttribute("q", q);
         } catch (RserveException e) {
             e.printStackTrace();
         }finally{
             connection.close();
         }
	}
}
