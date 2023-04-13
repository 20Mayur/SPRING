package com.Springboot.MySql.ShellScript;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class WindowsScriptReader implements ScriptReader{
    @Autowired
	private ScriptReaderFactory scriptReaderFactory;
	
	@Override
	public void ReaderScript(String Script) {
		// TODO Auto-generated method stub
		try {
			scriptReaderFactory.getScriptReader("windows",Script);
			ProcessBuilder Pb=new ProcessBuilder("cmd.exe","/c",Script);
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
