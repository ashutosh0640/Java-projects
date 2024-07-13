
  document.getElementById("registration_form").addEventListener("submit", (e) => {
    e.preventDefault();
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    alert("First Name: " + firstName + "\nLast Name: " + lastName);
});
