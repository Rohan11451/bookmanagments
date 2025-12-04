// script.js - Client-side validation and utilities

// Form validation
function validateBookForm() {
	let isValid = true;

	// Clear previous errors
	clearErrors();

	// Validate title
	const title = document.getElementById('title').value.trim();
	if (title === '') {
		showError('title-error', 'Book title is required');
		isValid = false;
	} else if (title.length < 2) {
		showError('title-error', 'Title must be at least 2 characters long');
		isValid = false;
	}

	// Validate author
	const author = document.getElementById('author').value.trim();
	if (author === '') {
		showError('author-error', 'Author name is required');
		isValid = false;
	} else if (author.length < 2) {
		showError('author-error', 'Author name must be at least 2 characters long');
		isValid = false;
	}

	// Validate price
	const price = document.getElementById('price').value;
	if (price === '' || parseFloat(price) < 0) {
		showError('price-error', 'Valid price is required');
		isValid = false;
	}

	// Validate quantity
	const quantity = document.getElementById('quantity').value;
	if (quantity === '' || parseInt(quantity) < 0) {
		showError('quantity-error', 'Valid quantity is required');
		isValid = false;
	}

	return isValid;
}

function showError(elementId, message) {
	const errorElement = document.getElementById(elementId);
	errorElement.textContent = message;
	errorElement.style.display = 'block';
}

function clearErrors() {
	const errorElements = document.querySelectorAll('.error-message');
	errorElements.forEach(element => {
		element.textContent = '';
		element.style.display = 'none';
	});
}

// Auto-hide alerts after 5 seconds
document.addEventListener('DOMContentLoaded', function() {
	const alerts = document.querySelectorAll('.alert');
	alerts.forEach(alert => {
		setTimeout(() => {
			alert.style.opacity = '0';
			alert.style.transition = 'opacity 0.5s ease';
			setTimeout(() => alert.remove(), 500);
		}, 5000);
	});

	// Add loading states to buttons
	const forms = document.querySelectorAll('form');
	forms.forEach(form => {
		form.addEventListener('submit', function() {
			const submitBtn = this.querySelector('button[type="submit"]');
			if (submitBtn) {
				submitBtn.innerHTML = '⏳ Processing...';
				submitBtn.disabled = true;
			}
		});
	});
});

// Search functionality enhancement
function enhanceSearch() {
	const searchInput = document.querySelector('.search-input');
	if (searchInput) {
		searchInput.addEventListener('input', function(e) {
			// You can add real-time search here with AJAX
			console.log('Search term:', e.target.value);
		});
	}
}

// Initialize when page loads
document.addEventListener('DOMContentLoaded', enhanceSearch);/**
 * 
 */