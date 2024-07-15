console.log("registration page out");

document.getElementById("registration_form").addEventListener("submit", (e) => {
    e.preventDefault();
    console.log("registration page in");
    
    const first_name = document.getElementById("first_name").value;
    const last_name = document.getElementById("last_name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const re_password = document.getElementById("re_password").value;
    const gender = document.querySelector('input[name="radiogroup1"]:checked').value;
    const state = document.getElementById("states").value;
    const country = document.getElementById("country").value;

    console.log(first_name+" "+last_name);
    console.log(email);
    console.log(password);
    console.log(re_password);
    console.log(gender);
    console.log(state);
    console.log(country);

    const data = {
        first_name: first_name,
        last_name: last_name,
        email: email,
        password: password,
        gender: gender,
        state: state,
        country: country
    }

    

    
});
