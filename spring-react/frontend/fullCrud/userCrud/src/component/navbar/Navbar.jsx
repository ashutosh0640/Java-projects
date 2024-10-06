import React,{ useContext, useEffect } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';

import Button from '@mui/material/Button';

import { loginInfoContext } from '../../contexts/login/LoginContext';

import './Navbar.css';

const Navbar = () => {
    const {loginInfo, setLoginInfo} = useContext(loginInfoContext);
    const navigate = useNavigate();

    useEffect(() => {
        if (!loginInfo.isLogin) {
            navigate('/login')
        }
    }, [loginInfo])

    const handleLogout = () => {
        setLoginInfo({ ...loginInfo, isLogin: false, id: null, userId: "", username: "", bio: "", image: "", dob: "", city: "" });
    }

    return (

        <div className="navbar">

            <div className='nav_child_1'>
                {loginInfo.username}
            </div>

            <div className='nav_child_2'>

                <ul>
                    <NavLink
                        to="/app/profile"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }

                    >
                        Profile
                    </NavLink>


                    <NavLink
                        to="/app/users"
                        className={({ isActive, isPending }) =>
                            `${isPending ? "pending" : isActive ? "active" : ""} navlink`
                        }
                    >
                        Users
                    </NavLink>
                    <NavLink
                        to="/app/setting"
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

                    <Button
                        onClick={handleLogout} className="logout_btn" variant="contained">Log out</Button>
                </ul>

            </div>

        </div>
    )
}

export default Navbar
