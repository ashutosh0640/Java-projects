import { useEffect } from 'react';
import './Profile.css';


const profile = () => {
    const url = `http://localhost:8080/${userID}`;


    useEffect(async () => {
        const response = await fetch(url);
        await response.json().then(data => {
            console.log(data);
        });
    })

    return (
        <div>
            <h1>Profile</h1>
            <div>
                <img src="" alt="" />
                <div>
                    <h3 className='name'></h3>
                    <p className='bio'></p>
                    <p className='dob'></p>
                    <p className='city'></p>
                </div>
            </div>

        </div>
    )
}

export default profile;
