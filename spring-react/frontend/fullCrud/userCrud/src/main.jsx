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
import Profile from './component/profile/Profile.jsx';
import { LoginInfoProvider } from './contexts/login/LoginContext.jsx';



createRoot(document.getElementById('root')).render(
  <LoginInfoProvider>

    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        <Route path="/app" element={<App />} >
          <Route path="profile" element={<Profile />} />

        </Route>
      </Routes>
    </Router>

  </LoginInfoProvider>

)
