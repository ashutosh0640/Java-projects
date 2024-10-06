import React, { useState, useContext } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Button from '@mui/material/Button';

import { loginInfoContext } from '../../contexts/login/LoginContext';

import './Login.css';

const Login = () => {

    const {loginInfo, setLoginInfo} = useContext(loginInfoContext);
    const navigate = useNavigate();

    const [login, setLogin] = useState({
        userId: '',
        password: ''
    });

    const handleLoginChange = (e) => {
        const { name, value } = e.target;
        setLogin(prevState => ({ ...prevState, [name]: value }));

    }



    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        const url = 'http://localhost:8080/user/login';
        try {
            const res = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(login)
            });

            if (!res.ok) {
                const errorData = await res.json();

                toast.error("Wrong credentials", {
                    position: 'top-right',
                    autoClose: 1000,
                });

                throw new Error(errorData.message || "Login failed");
            }else {
                const data = await res.json();
                setLoginInfo({ ...loginInfo, isLogin: true, id: data.id, userId: data.userId, username: data.name, bio: data.bio, image: data.image, dob: data.dateOfbirth, city: data.city });
                
                toast.success("Login successful", {
                    position: 'top-right',
                    autoClose: 1000,
                })

                setTimeout(() => {
                    navigate('/app');
                }, 1000);
            }
        } catch (err) {
            toast.error(err.message || "Check credentials", {
                position: 'top-right',
                autoClose: 1000,
            });
        }
    }

    return (

        <div className=' login'>

            <form onSubmit={handleLoginSubmit} action='' method='post'>

                <h1>Login Form</h1>


                <TextField
                    onChange={handleLoginChange}
                    id="outlined-basic-userId"
                    required
                    name='userId'
                    label="User Id"
                    variant="outlined"
                />

                <TextField
                    onChange={handleLoginChange}
                    id="outlined-basic-password"
                    required
                    name='password'
                    type='password'
                    label="Password"
                    variant="outlined"
                />

                <div className=' sign_btn_sec'>
                    <Button type='submit' variant="contained">Login</Button>
                    <NavLink
                        to="/signup"
                        className={({ isActive, isPending }) =>
                            isPending ? "pending" : isActive ? "active" : ""
                        }
                    >
                        Sign Up
                    </NavLink>
                </div>
            </form>
            <ToastContainer />

        </div>
    )
}

export default Login
