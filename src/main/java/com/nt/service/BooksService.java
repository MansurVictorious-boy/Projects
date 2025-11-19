package com.nt.service;

import java.util.List;

import com.nt.entity.BooksModule;

public interface BooksService {

	BooksModule custmerSaveBooks(BooksModule booksModule);

	BooksModule getByCustmerBookid(Long id);

	List<BooksModule> custmergetAllBooks();

}
