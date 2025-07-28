package com.InventoryManager.libraryInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InventoryManager.libraryInventory.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{
	Book findBybookNameIgnoreCase(String bookName);
}
