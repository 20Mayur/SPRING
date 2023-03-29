package com.Springboot.MySql.helper;

import org.springframework.http.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

@Component
public class FileUploadHelper {
	
	public final String UPLOAD_DIR="C:\\Users\\mkura\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\SpringMySql\\src\\main\\resources\\static\\image";

	public boolean uploadfile(MultipartFile multipartfile ) 
	{
		boolean f=false;
		
	   try {
		   
		/*InputStream is=multipartfile.getInputStream();
		byte data[]=new byte[is.available()];
		is.read(data);
		//wirte
		FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+multipartfile.getOriginalFilename());
		fos.write(data);
		 fos.flush();
		fos.close();
		f=true;*/
		   Files.copy(multipartfile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartfile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING );
		   f=true;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
		return f;
	
	}
	
	
	
}
