

function getUserInformation() {
    fetch("http://localhost:8080/user/name", {
        method: "GET",
        credentials: "include"
    })
}


document.getElementById("logoutButton").addEventListener("click", function () {
    fetch('http://localhost:8080/logout', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (response.ok) {
            console.log('Logout successful');
          } else {
            return response.json().then(data => {
              throw new Error(data.message);
            });
          }
        })
        .catch(error => console.error('Error:', error));
    
});