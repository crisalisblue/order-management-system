import { Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/UseAuthContext";

export default function LogedinRoute({children}){

    const {token} = useAuthContext()

    if(token) return <Navigate to="/"/>

    return(
     <>
        {children}
     </>
    );
}