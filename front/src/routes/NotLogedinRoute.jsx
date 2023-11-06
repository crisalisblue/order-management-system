import { Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/UseAuthContext";

export default function NotLogedinRoute() {
  const { token, loading } = useAuthContext();

  if (loading) return <h1>Cargando...</h1>;

  return <>{!token ? <Outlet /> : <Navigate to={"/"} />}</>;
}
