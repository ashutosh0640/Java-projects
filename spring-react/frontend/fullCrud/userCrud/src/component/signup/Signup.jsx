import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

import './Signup.css';
import { toast,ToastContainer } from 'react-toastify';

const Signup = () => {

    const [signUp, setSignUp] = useState({
        name: '',
        userId: '',
        password: '',
    });

    const handleSignUpchange = (e) => {
        const { name, value } = e.target;
        setSignUp({ ...signUp, [name]: value });
    }


    const handleSinupSubmit = async (e) => {
        e.preventDefault();
        const url = 'http://localhost:8080/user';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(signUp)
        });

        if(response.ok) {
            const data = await response.json();
            console.log("data success: ",data)
            toast.success("Sign up successful", {
                position: 'top-right',
                autoClose: 1000,
            })
        }else {

            toast.error("Sign up failed", {
                position: 'top-right',
                autoClose: 1000,
                })

            throw new Error(await response.json().message) || "Sign up failed"

        }
    }

    return (
        <div className='signup'>

            <form onSubmit={handleSinupSubmit} action='' method='post'>

                <h1>SignUp Form</h1>

                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='name'
                    id="outlined-basic-name"
                    label="Name"
                    variant="outlined"
                />


                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='userId'
                    id="outlined-basic-userId"
                    label="UserID"
                    variant="outlined"
                />


                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='password'
                    type='password'
                    id="outlined-basic-password"
                    label="Password"
                    variant="outlined"
                />


                <div className=' sign_btn_sec'>
                    <Button type='submit' variant="contained">Sign up</Button>
                    <NavLink
                        to="/login"
                        className={({ isActive, isPending }) =>
                            isPending ? "pending" : isActive ? "active" : ""
                        }
                    >
                        Login
                    </NavLink>
                </div>
            </form>
            <ToastContainer />
        </div>
    )
}

export default Signup
