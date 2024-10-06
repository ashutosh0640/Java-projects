import React from 'react';
import { NavLink } from 'react-router-dom';

import Button from '@mui/material/Button';

import './Navbar.css';

const Navbar = () => {
    return (
        <div className="navbar">

            <div className='nav_child_1'>
                Logo
            </div>

            <div className='nav_child_2'>

                <ul>
                    <NavLink
                        to="/profile"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }

                    >
                        Profile
                    </NavLink>


                    <NavLink
                        to="/users"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }
                    >
                        Users
                    </NavLink>
                    <NavLink
                        to="/contact"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }
                    >
                        Feeds
                    </NavLink>
                    <NavLink
                        to="/contact"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }
                    >
                        Setting
                    </NavLink>

                    <Button className="logout_btn" variant="contained">Log out</Button>
                </ul>

            </div>

        </div>
    )
}

export default Navbar
