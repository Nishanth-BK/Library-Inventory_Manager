package com.InventoryManager.libraryInventory.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.InventoryManager.libraryInventory.model.Book;
import com.InventoryManager.libraryInventory.repository.BookRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class BookService {
	
	BookRepository bookrepo;
	//create
	public String addBook(Book book){
		bookrepo.save(book);
		return "Book added "+book.getBookName();
	}
	//delete
	public void deleteBookById(int id) {
	    bookrepo.deleteById(id);
	}
	//read
	public List<Book> getAllBooks(){
		return bookrepo.findAll();
	}
	
	//update
//	public String updateBook(String name,Book book) {
//		Book existingBook = bookrepo.findBybookNameIgnoreCase(name);
//		if(existingBook==null) {
//			return "Book not found : "+name;
//		}
//		existingBook.setAuthor(book.getAuthor());
//		existingBook.setGenre(book.getGenre());
//		existingBook.setQuantity(book.getQuantity());
//		
//		bookrepo.save(existingBook);
//		return "Book details updated for "+name +" "+existingBook.getAuthor()+" "
//				+existingBook.getGenre()+" "+existingBook.getQuantity();
//	}
	
	//CSVParsing
	public void uploadBooksFromCSV(MultipartFile file) {
		try(Reader reader = new InputStreamReader(file.getInputStream())){
			CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
					.withType(Book.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			List<Book> books = csvToBean.parse();
			for(Book book:books) {
				bookrepo.save(book);
			}
		}
		catch(IOException e) {
			throw new RuntimeException("CSV Parsing Failed",e);
		}
	}
	
	public Book getBookById(int id) {
	    return bookrepo.findById(id)
	             .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
	}
	
	@Autowired
	public BookService(BookRepository bookrepo) {
		this.bookrepo = bookrepo;
	}
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
}
