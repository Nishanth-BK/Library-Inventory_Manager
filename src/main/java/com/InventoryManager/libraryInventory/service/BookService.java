package com.InventoryManager.libraryInventory.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.InventoryManager.libraryInventory.model.Book;
import com.InventoryManager.libraryInventory.repository.BookRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class BookService {
	//this is for dependency injection
	BookRepository bookrepo;
	//creating a Book obj and compares if the bookname already exists
	public String addBook(Book book) {
	    String key = book.getBookName().toLowerCase();
	    if (bookMap.containsKey(key)) {
	        return "Duplicate book: " + book.getBookName();
	    }
	    bookMap.put(key, book);
	    bookrepo.save(book);
	    return "Book added: " + book.getBookName();
	}


	//deletes the book(by Id)
	public void deleteBookById(int id) {
	    Book book = bookrepo.findById(id).orElse(null);
	    if (book != null) {
	        bookMap.remove(book.getBookName().toLowerCase());  // ? Remove from memory
	        bookrepo.deleteById(id);
	    }
	}

	//returns all the books
	public List<Book> getAllBooks(){
		return bookrepo.findAll();
	}
	//updates the existing fields. Finds by Id
	public void updateBook(Book updatedBook) {
		Book existingBook = bookrepo.findById(updatedBook.getId()).orElse(null);
		
		if(existingBook!=null) {
			existingBook.setBookName(updatedBook.getBookName());
			existingBook.setAuthor(updatedBook.getAuthor());
			existingBook.setGenre(updatedBook.getGenre());
			existingBook.setQuantity(updatedBook.getQuantity());
		}
		bookrepo.save(existingBook);
	}	
	//CSVParsing.
	public List<String> uploadBooksFromCSV(MultipartFile file) {
	    List<String> messages = new ArrayList<>();
	    try (Reader reader = new InputStreamReader(file.getInputStream())) {
	        CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
	                .withType(Book.class)
	                .withIgnoreLeadingWhiteSpace(true)
	                .build();

	        List<Book> books = csvToBean.parse();

	        for (Book book : books) {
	            String key = book.getBookName().toLowerCase();

	            if (!bookMap.containsKey(key)) {
	                bookMap.put(key, book);
	                bookrepo.save(book);
	                messages.add("Added: " + book.getBookName());
	            } else {
	                messages.add("Duplicate (skipped): " + book.getBookName());
	            }
	        }

	    } catch (IOException e) {
	        messages.add("CSV Parsing Failed: " + e.getMessage());
	    }
	    return messages;
	}
	
	public Book getBookById(int id) {
	    return bookrepo.findById(id)
	             .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
	}
	
	//for hashmap
	private Map<String, Book> bookMap = new HashMap<>();

	@Autowired
	public BookService(BookRepository bookrepo) {
	    this.bookrepo = bookrepo;

	    // Preload existing books into bookMap
	    List<Book> existingBooks = bookrepo.findAll();
	    for (Book book : existingBooks) {
	        bookMap.put(book.getBookName().toLowerCase(), book);
	    }
	}

	
}
