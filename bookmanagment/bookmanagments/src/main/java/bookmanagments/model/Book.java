package bookmanagments.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String author;
	private double price;
	private int quantity;
	private LocalDateTime createdAt;

	// Constructors
	public Book() {
	}

	public Book(String title, String author, double price, int quantity) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}
