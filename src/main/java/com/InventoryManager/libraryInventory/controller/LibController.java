package com.InventoryManager.libraryInventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	//made changes to return message
	@PostMapping("/books")
	public String saveBook(Book book, Model model) {
	    String result = bookService.addBook(book);
	    List<String> messages = new ArrayList<>();
	    messages.add(result);
	    model.addAttribute("messages", messages);
	    return "upload-result";  // Same HTML as used for CSV
	}


	@GetMapping("/")
	public String home() {
	    return "home";
	}
	//Made changes to display if duplicate values are entered
	@PostMapping("/upload-csv")
	public String csvParsing(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
	    // Validate file extension
	    if (!file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
	        redirectAttributes.addFlashAttribute("messages", List.of("Only CSV files are allowed!"));
	        return "redirect:/upload-result";
	    }

	    List<String> resultMessages = bookService.uploadBooksFromCSV(file);
	    model.addAttribute("messages", resultMessages);

	    return "upload-result";
	}

	//Parameters-> id and model. model for adding attribute to frontend
	@GetMapping("/edit-book")
	public String showEditForm(@RequestParam("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute(book);
		return "edit-form";
	}
	//book obj is the parameter, this is using it to update the values
	@PostMapping("/edit-book")
	public String SubmitForm(@ModelAttribute Book book) {
		bookService.updateBook(book);
		return "redirect:/books";
	}
	
	//this compares the id field to delete the id from db
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
