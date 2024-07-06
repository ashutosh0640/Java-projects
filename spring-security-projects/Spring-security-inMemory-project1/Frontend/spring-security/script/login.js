document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://221.171.86.64:8080/login', { // Backend server URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`,
        credentials: 'include'  // Include cookies in the request
    }).then(response => {
        if (response.ok) {
            window.location.href = 'http://221.171.86.63:8081/home';  // Redirect to home page on frontend server
        } else {
            document.getElementById('errorMessage').textContent = 'Invalid username or password';
        }
    }).catch(error => {
        console.error('Error:', error);
        document.getElementById('errorMessage').textContent = 'An error occurred. Please try again later.';
    });
});