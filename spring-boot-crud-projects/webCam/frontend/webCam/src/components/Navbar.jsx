import React from 'react'
import { NavLink } from 'react-router-dom'

const Navbar = () => {
    return (
        <div>
            <nav class="bg-blue-600 text-white px-4 py-3">
                <div class="container mx-auto flex flex-wrap items-center justify-between">
                    {/*  Brand Logo */}
                    <a href="#" class="text-xl font-bold">Hughes</a>

                    {/*  Hamburger Menu (Visible on small screens) */}
                    <button id="navbar-toggle" class="block lg:hidden">
                        <svg class="w-6 h-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7" />
                        </svg>
                    </button>

                    {/*  Navbar Links */}
                    <div id="navbar-menu" className="hidden lg:flex w-full lg:w-auto">
                        <ul className="flex flex-col lg:flex-row lg:space-x-6 text-sm lg:text-base">
                            {[
                                { name: "Home", path: "/" },
                                { name: "About", path: "/about" },
                                { name: "Service", path: "/service" },
                                { name: "Contact", path: "/contact" },
                            ].map((link, index) => (
                                <li key={index} className="group">
                                    <NavLink
                                        to={link.path}
                                        className="block px-3 py-2 hover:text-orange-500 transition duration-300"
                                        activeClassName="text-blue-600 font-semibold"
                                    >
                                        {link.name}
                                        {/* Animated underline */}
                                        <span className="block h-0.5 bg-orange-500 scale-x-0 group-hover:scale-x-100 transition-transform origin-left duration-300"></span>
                                    </NavLink>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </nav>

        </div>
    )
}

export default Navbar
