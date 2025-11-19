package com.nt.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.FileEntity;
import com.nt.repository.FileRepo;

@RestController
public class FIleController {

	
	@Autowired private FileRepo fileRepo;

	@PostMapping("/uploadfiles")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file)throws IOException {
		
		FileEntity fs=new FileEntity();
		fs.setFileName(file.getOriginalFilename());
		fs.setFileType(file.getContentType());
		fs.setData(file.getBytes());
		fileRepo.save(fs);
		return ResponseEntity.ok("file inserted successfully:"+file.getOriginalFilename());
	}
	
	@PostMapping("/uploadmultifiles")
	public ResponseEntity<List<Object>> uploadFileMulti(@RequestParam MultipartFile[] files)throws IOException{
		List<Object> response=Arrays.stream(files).map(s->{
			try {
				return  uploadFile(s);
			} catch (Exception e) {
				return "file upload failed"+e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
	
	
}
