import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";
import App from './App.jsx'
import './index.css'
import Login from './component/login/Login.jsx';
import Signup from './component/signup/Signup.jsx';

// const router = createBrowserRouter([
//   {
//     path: '/app',
//     element: <App />
//   },
//   {
//     path: '/login',
//     element: <Login />
//   },
//   {
//     path: '/signup',
//     element: <Signup />
//   },
//   {
//     path: '/',
//     element: <Login />
//   }
// ]);

createRoot(document.getElementById('root')).render(
  <Router>
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/app" element={<App />} />
    </Routes>
  </Router>
)
