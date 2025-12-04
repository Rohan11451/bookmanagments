package bookmanagments.controllers;

import javax.servlet.*;

import javax.servlet.http.*;

import bookmanagments.dao.BookDAO;
import bookmanagments.model.Book;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
	private BookDAO bookDAO;

	@Override
	public void init() {
		bookDAO = new BookDAO();
		System.out.println("BookServlet initialized with BookDAO");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action == null ? "list" : action) {
			case "new":
				showNewForm(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "delete":
				deleteBook(request, response);
				break;
			case "search":
				searchBooks(request, response);
				break;
			default:
				listBooks(request, response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException("Error processing request", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {
			case "insert":
				insertBook(request, response);
				break;
			case "update":
				updateBook(request, response);
				break;
			default:
				listBooks(request, response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException("Error processing request", e);
		}
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("books", bookDAO.getAllBooks());
		request.setAttribute("pageTitle", "All Books");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("pageTitle", "Add New Book");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-book.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookDAO.getBookById(id);

		if (existingBook != null) {
			request.setAttribute("book", existingBook);
			request.setAttribute("pageTitle", "Edit Book");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-book.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("books?error=Book not found");
		}
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Get form parameters
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// Validate input
		if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
			request.setAttribute("error", "Title and Author are required");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-book.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Create and save book
		Book newBook = new Book(title.trim(), author.trim(), price, quantity);
		boolean success = bookDAO.addBook(newBook);

		if (success) {
			response.sendRedirect("books?success=Book added successfully");
		} else {
			response.sendRedirect("books?error=Failed to add book");
		}
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// Validate input
		if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
			request.setAttribute("error", "Title and Author are required");
			request.setAttribute("book", bookDAO.getBookById(id));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-book.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Book book = new Book(title.trim(), author.trim(), price, quantity);
		book.setId(id);

		boolean success = bookDAO.updateBook(book);

		if (success) {
			response.sendRedirect("books?success=Book updated successfully");
		} else {
			response.sendRedirect("books?error=Failed to update book");
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = bookDAO.deleteBook(id);

		if (success) {
			response.sendRedirect("books?success=Book deleted successfully");
		} else {
			response.sendRedirect("books?error=Failed to delete book");
		}
	}

	private void searchBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");

		if (keyword != null && !keyword.trim().isEmpty()) {
			request.setAttribute("books", bookDAO.searchBooks(keyword.trim()));
			request.setAttribute("searchKeyword", keyword);
			request.setAttribute("pageTitle", "Search Results for: " + keyword);
		} else {
			request.setAttribute("books", bookDAO.getAllBooks());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/book-list.jsp");
		dispatcher.forward(request, response);
	}
}
