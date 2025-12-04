<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${pageTitle}</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class="container">
		<header class="header">
			<h1>📚 ${pageTitle}</h1>
			<p class="welcome-message">${welcomeMessage}</p>
		</header>

		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/books"
				class="btn btn-primary"> 📖 View All Books </a> <a
				href="${pageContext.request.contextPath}/books?action=new"
				class="btn btn-success"> ➕ Add New Book </a>
		</div>

		<div class="features">
			<div class="feature-card">
				<h3>Manage Books</h3>
				<p>Add, view, edit, and delete books from your collection</p>
			</div>
			<div class="feature-card">
				<h3>Search Functionality</h3>
				<p>Find books by title or author quickly</p>
			</div>
			<div class="feature-card">
				<h3>User Friendly</h3>
				<p>Clean and intuitive interface</p>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>