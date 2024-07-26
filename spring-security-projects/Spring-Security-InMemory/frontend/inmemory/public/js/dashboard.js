document.querySelector("#setting-btn").addEventListener('click', function () {
    console.log('setting');

    const settingDropdown = document.querySelector('#setting-dropdown');


})


document.querySelector("#logout").addEventListener('click', function () {
    console.log('logout');

    fetch('http://localhost:8080/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include', // Include cookies to ensure session is invalidated
    })
        .then(response => {
            if (response.ok) {
                // Redirect to the login page after successful logout
                var a = "logout successful";
                setTimeout(alert(a), 2000);
                window.location.href = '/login.html';
            } else {
                // Handle logout error
                console.error('Logout failed');
            }
        })
        .catch(error => {
            console.error('Error during logout:', error);
        });
})