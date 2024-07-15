console.log("login form out");
document.querySelector(".login").addEventListener("submit", (event)=>{
    console.log("login form in");
    event.preventDefault();
    const username = document.querySelector("#username").value;
    const password = document.querySelector("#password").value;

    const data = {
        username: username,
        password: password
    };

    console.log(data);

    fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then((response) => response.json())
    .then((data) => {
        console.log("Success: ",data);
        alert("Login successful!");
    })
    .catch((error) => {
        console.error("Error:", error);
        alert("login failed");
    });
    
});