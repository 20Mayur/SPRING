package com.Spark.SaprkRDD;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class SparkCsvController {
	
	@Autowired
    private SparkSession sparkSession;

   @GetMapping("/csv-psv-tsv")
    public ResponseEntity<String> readCsv() {
    	Dataset<Row> csvData = sparkSession.read()
                //.option("header", "true") // Use first row as header
                .option("inferSchema", "true") // Infer data types automatically
                .csv("C:\\Users\\mkura\\Downloads\\SampleCSVFile_2kb.csv"); 
    	Dataset<Row> psvData=sparkSession.read()
    			.option("inferSchema","true")
    			.csv("C:\\Users\\mkura\\Downloads\\output.psv");	
    	Dataset<Row> tsvData=sparkSession.read()
    			.option("inferSchema","true")
    			.csv("C:\\Users\\mkura\\Downloads\\output.tsv");
    	Dataset<Row> excelData=sparkSession.read()
    			.format("com.creatilycs.spark.excel")
    			.option("inferSchema","true")
    			.option("header","true")
    			.csv("C:\\Users\\mkura\\Downloads\\SampleCSVFile_2kb.xlsx");
    	Dataset<Row> jsonData=sparkSession.read()
    			.option("inferSchema","true")
    			.csv("C:\\Users\\mkura\\Downloads\\csvjson.json");
    	csvData.show();
    	psvData.show();
    	tsvData.show();
    	excelData.show();
    	jsonData.show();
    	
    	
    	return ResponseEntity.ok("read Successfully");
    		
    	}
    }
	


