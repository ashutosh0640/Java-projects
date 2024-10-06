import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

import './Signup.css';

const Signup = () => {

    const [signUpInfo, setSignUpInfo] = useState({
        name: '',
        userId: '',
        password: '',
    });

    const handleSignUpchange = (e) => {
        const { name, value } = e.target;
        setSignUpInfo({ ...signUpInfo, [name]: value });
    }

    
    const handleSinupSubmit = (e) => {
        e.preventDefault();
        console.log(signUpInfo);
        // API call to sign up
    }

    return (
        <div className='signup'>

            <form onSubmit={handleSinupSubmit} action='' method='post'>

                <h1>SignUp Form</h1>

                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='name'
                    id="outlined-basic"
                    label="Name"
                    variant="outlined"
                />


                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='userId'
                    id="outlined-basic"
                    label="UserID"
                    variant="outlined"
                />


                <TextField
                    onChange={handleSignUpchange}
                    required
                    name='password'
                    type='password'
                    id="outlined-basic"
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
        </div>
    )
}

export default Signup
