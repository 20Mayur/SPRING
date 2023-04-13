package com.Springboot.MySql.ShellScript;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScriptController {
	
	
	@Autowired
	private ScriptReaderFactory scriptReaderFactory;
	
	Logger logger=LoggerFactory.getLogger(ScriptController.class);
	
	 @GetMapping("/script/{scriptType}")
	    public String getScript(@RequestParam("path") String scriptPath,@PathVariable String scriptType) throws IOException {
		 logger.info("inside getScript Method");
		 StringBuilder scriptContent = new StringBuilder();
	        ScriptReader scriptReader=scriptReaderFactory.getScriptReader(scriptType,scriptPath);
	        scriptReader.ReaderScript(scriptPath);
	        return scriptContent.toString();
	    }
	

}
