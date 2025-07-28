package com.InventoryManager.libraryInventory.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.InventoryManager.libraryInventory.model.Book;
import com.InventoryManager.libraryInventory.service.BookService;

@Controller
public class LibController {
	private BookService bookService;
	
	@GetMapping("/books")
	public String getAllBooks(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("booklist",books);
		return "book-list";
	}
	
	@GetMapping("/add-book")
	public String addNewBook(Model model) {
		model.addAttribute("newBook",new Book());
		return "add-book";
	}
	@PostMapping("/books")
	public String saveBook(Book book) {
		bookService.addBook(book);
		return "redirect:/books";
	}
	@GetMapping("/")
	public String home() {
	    return "home";
	}
	@PostMapping("/upload-csv")
	public String csvParsing(@RequestParam("file") MultipartFile file) {
		bookService.uploadBooksFromCSV(file);
		return "redirect:/books";
	}
	@GetMapping("/edit-book")
	public String showEditForm(@RequestParam("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute(book);
		return "edit-form";
	}
	@PostMapping("/edit-book")
	public String SubmitForm(@ModelAttribute Book book) {
		bookService.updateBook(book);
		return "redirect:/books";
	}
	@GetMapping("/delete-book")
	public String deleteBook(@RequestParam("id") int id) {
		bookService.deleteBookById(id);
		return "redirect:/books";
	}
	@GetMapping("/login")
	public String login() {
	    return "login"; 
	}

	
	LibController(BookService bookService){
		this.bookService=bookService;
	}
}
