import { useState } from "react";
import { authContext as auth } from "./AuthContext";

const authContext = auth

export default function AuthProvider({children}){
    const [user, setUser] = useState({
        username: '',
        password: ''
    });
    const [token, setToken] = useState('123');
    const [loading, setLoading] = useState(true);

    
    return(
     <authContext.Provider value={{token, user, loading}}>
         {children}
     </authContext.Provider>
    );
}