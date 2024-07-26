
document.getElementById('loginForm').addEventListener('submit', function (event) {
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
                var a = "login successful";
                setTimeout(alert(a), 2000);
                window.location.href = '/home.html';
            } else {
                document.getElementById('error').innerText = 'Invalid username or password';
            }
        })
        .catch(error => {
            document.getElementById('error').innerText = `Error: ${error}`;
        });
});