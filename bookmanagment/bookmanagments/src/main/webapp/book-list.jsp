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
			<h1>${pageTitle}</h1>

			<!-- Success/Error Messages -->
			<c:if test="${not empty param.success}">
				<div class="alert alert-success">✅ ${param.success}</div>
			</c:if>
			<c:if test="${not empty param.error}">
				<div class="alert alert-error">❌ ${param.error}</div>
			</c:if>
		</header>

		<div class="action-bar">
			<a href="${pageContext.request.contextPath}/books?action=new"
				class="btn btn-success"> ➕ Add New Book </a> <a
				href="${pageContext.request.contextPath}/home"
				class="btn btn-secondary"> 🏠 Home </a>

			<!-- Search Form -->
			<form action="${pageContext.request.contextPath}/books" method="get"
				class="search-form">
				<input type="hidden" name="action" value="search"> <input
					type="text" name="keyword"
					placeholder="Search by title or author..." value="${searchKeyword}"
					class="search-input">
				<button type="submit" class="btn btn-info">🔍 Search</button>
				<c:if test="${not empty searchKeyword}">
					<a href="${pageContext.request.contextPath}/books"
						class="btn btn-secondary">Clear</a>
				</c:if>
			</form>
		</div>

		<c:choose>
			<c:when test="${not empty books}">
				<div class="table-container">
					<table class="book-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th>Author</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items="${books}">
								<tr>
									<td>${book.id}</td>
									<td>${book.title}</td>
									<td>${book.author}</td>
									<td>$${book.price}</td>
									<td><span
										class="quantity ${book.quantity == 0 ? 'out-of-stock' : book.quantity < 5 ? 'low-stock' : ''}">
											${book.quantity} </span></td>
									<td class="action-buttons"><a
										href="${pageContext.request.contextPath}/books?action=edit&id=${book.id}"
										class="btn btn-edit" title="Edit Book"> ✏️ Edit </a> <a
										href="${pageContext.request.contextPath}/books?action=delete&id=${book.id}"
										class="btn btn-delete"
										onclick="return confirm('Are you sure you want to delete \"
										${book.title}\"?')"
                                           title="Delete Book">
											🗑️ Delete </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="summary">
					<p>
						Total Books: <strong>${books.size()}</strong>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="empty-state">
					<h3>No books found</h3>
					<p>There are no books in the database yet.</p>
					<a href="${pageContext.request.contextPath}/books?action=new"
						class="btn btn-success"> ➕ Add Your First Book </a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>