import { useContext } from "react";
import { authContext } from "./AuthContext";

export const useAuthContext = () => useContext(authContext);
