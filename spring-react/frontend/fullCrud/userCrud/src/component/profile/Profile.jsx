import { useContext, useEffect } from 'react';
import './Profile.css';
import { loginInfoContext } from '../../contexts/login/LoginContext';

import Card from '../card/ProfileCard';


const profile = ({firstLetter, fullName, img, bio, city}) => {
    const {loginInfo, setLoginInfo} = useContext(loginInfoContext);
    console.log("login info: ", loginInfo)

    
    return (
        <div className=' border-2 border-red-500'>

            <h1>This is profile Section</h1>
            
            {/* <Card
             firstLetter={loginInfo.name.trim().split(" ")[0][0]}
             fullName={loginInfo.name.trim()}
             img={loginInfo.img}
             bio={loginInfo.bio}
             city={loginInfo.city}
             /> */}

        </div>
    )
}

export default profile;
