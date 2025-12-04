<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
		</header>

		<div class="form-container">
			<form action="${pageContext.request.contextPath}/books" method="post"
				onsubmit="return validateBookForm()" class="book-form">
				<input type="hidden" name="action" value="insert">

				<div class="form-group">
					<label for="title">Book Title: *</label> <input type="text"
						id="title" name="title" required placeholder="Enter book title"
						class="form-control"> <span class="error-message"
						id="title-error"></span>
				</div>

				<div class="form-group">
					<label for="author">Author: *</label> <input type="text"
						id="author" name="author" required placeholder="Enter author name"
						class="form-control"> <span class="error-message"
						id="author-error"></span>
				</div>

				<div class="form-group">
					<label for="price">Price: *</label> <input type="number" id="price"
						name="price" step="0.01" min="0" required placeholder="0.00"
						class="form-control"> <span class="error-message"
						id="price-error"></span>
				</div>

				<div class="form-group">
					<label for="quantity">Quantity: *</label> <input type="number"
						id="quantity" name="quantity" min="0" required placeholder="0"
						class="form-control"> <span class="error-message"
						id="quantity-error"></span>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-success">➕ Add Book</button>
					<a href="${pageContext.request.contextPath}/books"
						class="btn btn-secondary">← Back to List</a>
					<button type="reset" class="btn btn-warning">🔄 Clear</button>
				</div>
			</form>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>