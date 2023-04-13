package com.Springboot.MySql.ShellScript;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScriptReaderFactory {
	@Autowired
	private PythonScriptReader pythonScriptReader;
	@Autowired
	private UnixScriptReader unixScriptReader;
	@Autowired
	private WindowsScriptReader windowsScriptReader;
	public  ScriptReader getScriptReader(String scriptType ,String scriptPath) {
        switch (scriptType) {
            case "python":
                return  pythonScriptReader;
            case "unix":
                return  unixScriptReader;
            case "windows":
                return  windowsScriptReader;
            default:
                throw new IllegalArgumentException("Invalid script type");
        }
    }
}
