import React, { useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Button from '@mui/material/Button';

import './Login.css';

const Login = () => {

    const navigate = useNavigate();

    const [loginInfo, setLoginInfo] = useState({
        userId: '',
        password: ''
    });

    const handleLoginChange = (e) => {
        const { name, value } = e.target;
        setLoginInfo(prevState => ({ ...prevState, [name]: value }));

    }



    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        console.log("Login info: ", loginInfo)
        const url = 'http://localhost:8080/user/login';
        try {
            const res = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(loginInfo)
            });

            if (!res.ok) {
                const errorData = await res.json();
                throw new Error(errorData.message || "Login failed");
            }

            const data = await res.json();
            console.log("data: ", data);

            if(data) {
                setTimeout(() => {
                    navigate('/app');
                }, 2000);
                
            }


            if (data) {
                toast.success("Login successful", {
                    position: 'top-right',
                    autoClose: 2000,
                })
            } else {

                toast.error("Wrong credentials", {
                    position: 'top-right',
                    autoClose: 2000,
                });
            }


        } catch (err) {
            toast.error(err.message || "Check credentials", {
                position: 'top-right',
                autoClose: 2000,
            });
        }
    }

    return (
        <div className=' login'>

            <form onSubmit={handleLoginSubmit} action='' method='post'>

                <h1>Login Form</h1>


                <TextField
                    onChange={handleLoginChange}
                    id="outlined-basic"
                    required
                    name='userId'
                    label="User Id"
                    variant="outlined"
                />

                <TextField
                    onChange={handleLoginChange}
                    id="outlined-basic"
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
