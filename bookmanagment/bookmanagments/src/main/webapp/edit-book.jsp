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

			<c:if test="${not empty error}">
				<div class="alert alert-error">❌ ${error}</div>
			</c:if>
		</header>

		<div class="form-container">
			<form action="${pageContext.request.contextPath}/books" method="post"
				onsubmit="return validateBookForm()" class="book-form">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="id" value="${book.id}">

				<div class="form-group">
					<label for="title">Book Title: *</label> <input type="text"
						id="title" name="title" value="${book.title}" required
						placeholder="Enter book title" class="form-control"> <span
						class="error-message" id="title-error"></span>
				</div>

				<div class="form-group">
					<label for="author">Author: *</label> <input type="text"
						id="author" name="author" value="${book.author}" required
						placeholder="Enter author name" class="form-control"> <span
						class="error-message" id="author-error"></span>
				</div>

				<div class="form-group">
					<label for="price">Price: *</label> <input type="number" id="price"
						name="price" value="${book.price}" step="0.01" min="0" required
						placeholder="0.00" class="form-control"> <span
						class="error-message" id="price-error"></span>
				</div>

				<div class="form-group">
					<label for="quantity">Quantity: *</label> <input type="number"
						id="quantity" name="quantity" value="${book.quantity}" min="0"
						required placeholder="0" class="form-control"> <span
						class="error-message" id="quantity-error"></span>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary">💾 Update
						Book</button>
					<a href="${pageContext.request.contextPath}/books"
						class="btn btn-secondary">← Back to List</a>
					<button type="reset" class="btn btn-warning">🔄 Reset</button>
				</div>
			</form>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>