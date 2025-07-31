package com.InventoryManager.libraryInventory.model;

import com.opencsv.bean.CsvBindByPosition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@CsvBindByPosition(position=0)
	private String bookName;

	@CsvBindByPosition(position=1)
	private String author;

	@CsvBindByPosition(position=2)
	private String genre;

	@CsvBindByPosition(position=3)
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", genre=" + genre + ", quantity="
				+ quantity + "]";
	}
	public Book( String bookName, String author, String genre, int quantity) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.quantity = quantity;
	}
	
	//for jpa
	public Book() {
		
	}
}
