package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.BooksExcelFile;

public interface BooksExcelFileRepo extends JpaRepository<BooksExcelFile, Long> {

}
