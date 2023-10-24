import { Outlet } from "react-router-dom";
import { Link as RouterLink } from "react-router-dom";

export const Usuarios = () => {
  return (
    <>
      <a href="/usuarios">Lista de Usuarios</a> <a href="/usuarios/nuevo">Agregar Usuario</a>
      <Outlet />
    </>
  );
};
