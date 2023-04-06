package com.Spark.SaprkRDD;

import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ReadWrite")
public class MySqlController  {
	    
	     @Autowired
	     private SparkSession sparkSession;
	     
	     
	    

	     @GetMapping("/csv")
	     public ResponseEntity<String> readAndwrite(){
	    	 
	    	 Properties connectionProperties = new Properties();
	    	 connectionProperties.put("url","jdbc:mysql://localhost:3306/spark");
	    	 connectionProperties.put("user", "root");
	    	 connectionProperties.put("password", "Mayur@123");
	    	 connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver");
	    	 Dataset<Row> csvdata=sparkSession.read()
	    			 .option("interSchema","true")
	    			 .csv("C:\\Users\\mkura\\Downloads\\SampleCSVFile_2kb.csv");
	    	 
	    	 csvdata.write()
	    	 .format("jdbc")
	    	 .mode(SaveMode.Append)
	    	 .jdbc("jdbc:mysql://localhost:3306/spark","sparkcsv",connectionProperties);
	    	 
	    	 Dataset<Row> psvdata=sparkSession.read()
	    			 .option("interSchema","true")
	    			 .csv("C:\\Users\\mkura\\Downloads\\output.psv");
	    	 
	    	 psvdata.write()
	    	 .format("jdbc")
	    	 .mode(SaveMode.Append)
	    	 .jdbc("jdbc:mysql://localhost:3306/spark","sparkpsv",connectionProperties);
	    	 
	    	 Dataset<Row> tsvdata=sparkSession.read()
	    			 .option("interSchema","true")
	    			 .csv("C:\\Users\\mkura\\Downloads\\output.tsv");
	    	 
	    	 tsvdata.write()
	    	 .format("jdbc")
	    	 .mode(SaveMode.Append)
	    	 .jdbc("jdbc:mysql://localhost:3306/spark","sparktsv",connectionProperties);
	    	 
	    	 
	    	 Dataset<Row> exceldata=sparkSession.read()
	    			 .format("com.crealytics.spark.excel")
	    			 .option("header","true")
	    			 .option("interSchema","true")
	    			 .load("C:\\Users\\mkura\\Downloads\\file_example_XLSX_50.xlsx");
	    	 
	    	 exceldata.write()
	    	 .format("jdbc")
	    	 .mode(SaveMode.Append)
	    	 .jdbc("jdbc:mysql://localhost:3306/spark","sparkexcel",connectionProperties);
	    	 
	    	 
	    	 Dataset<Row> jsondata=sparkSession.read()
	    			 .option("interSchema","true")
	    			 .option("multiline", "true")
	    			 .option("DROPMALFORMED","true")
	    	     	 .json("C:\\Users\\mkura\\Downloads\\file\\dwsample.json");
	    	 Dataset<String> jsonString=jsondata.toJSON();
	    	 jsonString.write()
	    	 .format("jdbc")
	    	 .mode(SaveMode.Append)
	    	 .jdbc("jdbc:mysql://localhost:3306/spark","sparkjson",connectionProperties);
	    	 
	    	 return ResponseEntity.ok("Reading and writting done ");
	    	         
	     }
	     
	     
         
	 
}
