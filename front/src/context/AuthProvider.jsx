import { useEffect, useState } from "react";
import { authContext } from "./AuthContext";
import {
  deleteUserToken,
  getUserToken,
  saveUserToken,
} from "../services/LocalStorageService";
import { login } from "../api/UserAPI";

export default function AuthProvider({ children }) {
  const [token, setToken] = useState("123");
  const [loading, setLoading] = useState(true);

  const loginUser = (user) => {
    setLoading(false);
    return login(user)
      .then((token) => {
        saveUserToken(token);
        setToken(token);
      })
      .catch((err) => Promise.reject(err));
  };

  const logout = () => {
    setToken(null);
    deleteUserToken();
  };

  useEffect(() => {
    const userToken = getUserToken();
    setToken(userToken);
    setLoading(false);
  }, []);

  return (
    <authContext.Provider value={{ token, loading, loginUser, logout }}>
      {children}
    </authContext.Provider>
  );
}
