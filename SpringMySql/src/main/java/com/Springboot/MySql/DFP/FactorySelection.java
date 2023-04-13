package com.Springboot.MySql.DFP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class FactorySelection {
    @Autowired
	private SqlCrud sqlCrud;
	@Autowired
	private MongoCrud mongoCrud;
	
	public DesignF getDataBase(String DBType) {
	    try {
	        if(DBType==null) {
	            return null;
	        }
	        if(DBType.equalsIgnoreCase("MySql")) {
	            return sqlCrud;
	        }
	        else if(DBType.equalsIgnoreCase("Mongo")) {
	            return mongoCrud;
	        }
	        return null;
	    } catch (Exception e) {
	        // Handle any exceptions that occur
	        System.err.println("Error occurred while getting database: " + e.getMessage());
	        return null;
	    }
	}
}
