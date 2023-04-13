package com.Springboot.MySql.ShellScript;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class PythonScriptReader implements ScriptReader {
   @Autowired
	private ScriptReaderFactory scriptReaderFactory;
   
   Logger logger=LoggerFactory.getLogger(PythonScriptReader.class);
	@Override
	public void ReaderScript(String Script) {
		// TODO Auto-generated method stub
		try {
			scriptReaderFactory.getScriptReader("python",Script);
			logger.info("ProcessBuilder Started");
			ProcessBuilder Pb=new ProcessBuilder("py",Script);
			logger.info("Process started");
			Process p=Pb.start();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = reader.readLine();
	        while (line != null) {
	            System.out.println(line);
	            line = reader.readLine();
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
