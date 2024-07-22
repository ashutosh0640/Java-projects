document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    console.log(username, password);

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`,
        credentials: 'include',
    })
    .then(response => {
        if (response.ok) {
            window.location.href = 'http://127.0.0.1:5500/inmemory/home.html';
        } else {
            document.getElementById('error').innerText = 'Invalid username or password';
        }
    })
    .catch(error => {
        document.getElementById('error').innerText = `Error: ${error}`;
    });
});