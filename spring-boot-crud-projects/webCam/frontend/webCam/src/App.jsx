
import Contact from './components/Contact'
import Home from './components/Home'
import './App.css'
import AppLayout from './layout/AppLayout'
import About from './components/About'
import Service from './components/Service'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

const router = createBrowserRouter([
  {
    element: <AppLayout />,
    children: [
      {
        path:"/",
        element:<Home />
      },
      {
        path:"/contact",
        element:<Contact />
      },
      {
        path: "/about",
        element: <About />
      },
      {
        path: "/service",
        element: <Service />
      }
    ]
  }

]);
  

function App() {
  return (
    <>
    <RouterProvider router={router} />
    </>
  )
}


export default App
