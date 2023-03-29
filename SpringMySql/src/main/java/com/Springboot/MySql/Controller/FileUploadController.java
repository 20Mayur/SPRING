package com.Springboot.MySql.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Springboot.MySql.helper.FileUploadHelper;

import org.springframework.core.io.Resource;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadfile(@RequestParam("file") MultipartFile file ){
		
		/*System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());*/
		
		try {
		
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
		}
		
		if(!file.getContentType().equals("image/jpeg")) 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain JPEG file");
		}
		
		boolean f=fileUploadHelper.uploadfile(file);
		if(f) {
			return ResponseEntity.ok("file is succesfully upload");
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong please try again ");
		
		
	}
	@GetMapping("/download-file")
		public ResponseEntity<Resource> handleFileDownload() throws IOException {
		       
	        Resource resource = new FileSystemResource("C:\\Users\\mkura\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\SpringMySql\\src\\main\\resources\\static\\image\\IMG-20190802-WA0004.jpg");
	        
	        if(!resource.exists()) {
	            throw new FileNotFoundException("File not found: " + resource.getFilename());
	        }
	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	
	/*@GetMapping("/download")
		public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) throws IOException {
		    Resource resource = new FileSystemResource(filePath);
		    
		    if(!resource.exists()) {
		        throw new FileNotFoundException("File not found: " + filePath);
		    }
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		    headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
		    headers.add(HttpHeaders.PRAGMA, "no-cache");
		    headers.add(HttpHeaders.EXPIRES, "0");
		    
		    return ResponseEntity.ok()
		            .headers(headers)
		            .contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .contentLength(resource.contentLength())
		            .body(resource);
		}*/
	

	@GetMapping("/directory")
    public List<List<String>> getDirectoryContents(@RequestParam String path) throws Exception {
        path = URLDecoder.decode(path, "UTF-8");
        File directory = new File(path);
        String[] contents = directory.list();
        if (contents == null) {
            throw new Exception("Failed to get contents of directory: " + path);
        }
        List<String> folders = new ArrayList<>();
        List<String> files = new ArrayList<>();
        for (String content : contents) {
            File f = new File(directory, content);
            if (f.isDirectory()) {
                folders.add(content);
            } else if (f.isFile()) {
                files.add(content);
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(folders);
        result.add(files);
        return result;
    }

	
	}
	

