package bookmanagments.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bookmanagments.model.*;
import bookmanagments.utils.DatabaseUtil;

public class BookDAO {

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books ORDER BY id DESC";

		try (Connection conn = DatabaseUtil.getConnection();

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Book book = extractBookFromResultSet(rs);
				books.add(book);
			}
		} catch (SQLException e) {
			System.err.println("Error getting all books: " + e.getMessage());
			e.printStackTrace();
		}
		return books;
	}

	public Book getBookById(int id) {
		Book book = null;
		String sql = "SELECT * FROM books WHERE id = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					book = extractBookFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error getting book by ID: " + e.getMessage());
			e.printStackTrace();
		}
		return book;
	}

	public boolean addBook(Book book) {
		String sql = "INSERT INTO books (title, author, price, quantity) VALUES (?, ?, ?, ?)";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setDouble(3, book.getPrice());
			stmt.setInt(4, book.getQuantity());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error adding book: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBook(Book book) {
		String sql = "UPDATE books SET title = ?, author = ?, price = ?, quantity = ? WHERE id = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setDouble(3, book.getPrice());
			stmt.setInt(4, book.getQuantity());
			stmt.setInt(5, book.getId());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error updating book: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteBook(int id) {
		String sql = "DELETE FROM books WHERE id = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error deleting book: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public List<Book> searchBooks(String keyword) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? ORDER BY id DESC";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			String searchPattern = "%" + keyword + "%";
			stmt.setString(1, searchPattern);
			stmt.setString(2, searchPattern);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Book book = extractBookFromResultSet(rs);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error searching books: " + e.getMessage());
			e.printStackTrace();
		}
		return books;
	}

	private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setPrice(rs.getDouble("price"));
		book.setQuantity(rs.getInt("quantity"));

		// Handle timestamp conversion
		Timestamp timestamp = rs.getTimestamp("created_at");
		if (timestamp != null) {
			book.setCreatedAt(timestamp.toLocalDateTime());
		}

		return book;
	}
}
