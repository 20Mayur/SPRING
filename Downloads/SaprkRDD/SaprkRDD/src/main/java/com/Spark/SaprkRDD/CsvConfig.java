package com.Spark.SaprkRDD;


import org.apache.spark.sql.SparkSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class CsvConfig {
 
	  @Bean
	  public SparkSession sparkSession() {
		  return SparkSession
				  .builder()
				  .appName("Intergrating spring boot with apache spark")
				  .master("local[*]")
				  .getOrCreate();
	  }
	  
	
	  
}
