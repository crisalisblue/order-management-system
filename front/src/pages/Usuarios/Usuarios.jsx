import { Outlet } from "react-router-dom";
import { Link as RouterLink } from "react-router-dom";

export const Usuarios = () => {
  return (
    <>
      <section className={"flex"} id={"usuarios"}>
        <RouterLink to="/usuarios">Lista de Usuarios</RouterLink>
        <RouterLink to="/usuarios/nuevo">Agregar Usuario</RouterLink>
      </section>
      <Outlet />
    </>
  );
};
