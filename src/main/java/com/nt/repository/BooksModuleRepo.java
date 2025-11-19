package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.BooksModule;

public interface BooksModuleRepo  extends JpaRepository<BooksModule,Long>{

}
