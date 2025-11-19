package com.nt.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BooksExcelFile;
import com.nt.repository.BooksExcelFileRepo;
import com.nt.service.BooksExcelUploadService;
import com.nt.utility.Helper;

@Service
public class BooksExcelUploadServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepo booksExcelFileRepo;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {
		List<BooksExcelFile> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());
		booksExcelFileRepo.saveAll(excelFilesSaveDatabase);
	}

//	@Override
//	public void uploadExcelintoDB(MultipartFile file) throws IOException {
//
//		List<BooksExcelFile> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());
//
//		booksExcelFileRepo.saveAll(excelFilesSaveDatabase);
//
//	}

}
