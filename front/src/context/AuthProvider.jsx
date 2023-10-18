import { useEffect, useState } from "react";
import { authContext} from "./AuthContext";
import { getUserToken, saveUserToken } from "../services/LocalStorageService";
import { login } from "../services/UserService";


export default function AuthProvider({children}){
    const [token, setToken] = useState('123');
    const [loading, setLoading] = useState(true);

    const loginUser = (user) => {
        setLoading(true);
        return login(user).then( token =>{
          saveUserToken(token);
          setToken(token);
          setLoading(false);
        })
        .catch(err => Promise.reject(err));
    }

    useEffect(() =>{
        const userToken = getUserToken();
        setToken(userToken);
        setLoading(false);
    }, [])
  
    
    return(
     <authContext.Provider value={{token, loading, loginUser}}>
         {children}
     </authContext.Provider>
    );
}