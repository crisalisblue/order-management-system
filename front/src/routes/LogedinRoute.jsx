import { Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/UseAuthContext";

export default function LogedinRoute(){

    const {token} = useAuthContext()

    if(!token) return <Navigate to="/login"/>

    return(
     <>
        <Outlet/> 
     </>
    );
}