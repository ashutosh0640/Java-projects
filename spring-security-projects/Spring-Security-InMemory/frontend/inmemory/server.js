const express = require('express');
const path = require('path');
const app = express();
const port = 8081;
// const cors = require('cors');


// const corsOptions = {
//   origin: ['http://localhost:8080'], // Allow requests from Spring Boot
//   methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
//   allowedHeaders: '*',
//   credentials: true,
// };
// app.use(cors(corsOptions));


const cors = require('cors');

const corsOptions = {
    origin: ['http://localhost:8080/'],
    credentials: true,
    optionSuccessStatus: 200
}

app.use(cors(corsOptions));

app.use(function (req, res, next) {
    res.header('Access-Control-Allow-Origin', "http://localhost:8080");
    res.header('Access-Control-Allow-Headers', true);
    res.header('Access-Control-Allow-Credentials', true);
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    next();
});


// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Handle SPA routing, send the main index.html file for all routes
app.get('/login', (req, res) => res.sendFile(path.join(__dirname, 'public', 'login.html')));

//app.get('/home', (req, res) => res.sendFile(path.join(__dirname, 'public', 'home.html')));

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});