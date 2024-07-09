console.log("hello world");


document.getElementById('loginForm').addEventListener("submit", function(event) {
    event.preventDefault();
    const user = document.getElementById("user").value;
    const pass = document.getElementById("pass").value;

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({user, pass})
    })
    .then(response => {
        if (response.ok) {
            window.location.href = 'http://127.0.0.1:5500/src/home.html';
        }
    })
})


