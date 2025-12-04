package bookmanagments.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = { "", "/home", "/index" })
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set page attributes
		request.setAttribute("pageTitle", "Book Store - Home");
		request.setAttribute("welcomeMessage", "Welcome to our Book Management System");
		request.setAttribute("totalBooks", 0); // You can calculate this from DAO

		// Forward to home page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
