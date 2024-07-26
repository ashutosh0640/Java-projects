const express = require('express');
const path = require('path');
const cors = require('cors');
const app = express();
const port = 8081;

const corsOptions = {
  origin: ['http://localhost:8080'], // Allow requests from Spring Boot
  methods: 'GET,HEAD,PUT,PATCH,POST,DELETE,OPTIONS',
  allowedHeaders: '*',
  credentials: true,
};
app.use(cors(corsOptions));


// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Handle SPA routing, send the main index.html file for all routes
app.get('/login', (req, res) => res.sendFile(path.join(__dirname, 'public', 'login.html')));

//app.get('/home', (req, res) => res.sendFile(path.join(__dirname, 'public', 'home.html')));

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});