// import React, { createContext, useState } from 'react';

// export const loginInfoContext = createContext({
//     "id": null,
//     "userId": "",
//     "username": "",
//     "bio":"",
//     "image":"",
//     "dob":"",
//     "city":"",
//     "isLogin": false
// });

// export const LoginInfoProvider = ({ children }) => {
//     const [loginInfo, setLoginInfo] = useState({});
//     return (
//         <loginInfoContext.Provider value={{ loginInfo, setLoginInfo }}>
//             {children}
//         </loginInfoContext.Provider>
//     );
// }


import React, { createContext, useState } from 'react';

export const loginInfoContext = createContext({
    "id": null,
    "userId": "",
    "username": "",
    "bio": "",
    "image": "",
    "dob": "",
    "city": "",
    "isLogin": false
});

export const LoginInfoProvider = ({ children }) => {
    const [loginInfo, setLoginInfo] = useState({});
    return (
        <loginInfoContext.Provider value={{ loginInfo, setLoginInfo }}>
            {children}
        </loginInfoContext.Provider>
    );
};
