import { Outlet } from "react-router-dom";
import { Link as RouterLink, useLocation } from "react-router-dom";

export const Usuarios = () => {
  const location = useLocation();
  return (
    <>
      <section className="tabs">
        <RouterLink
          className={`tab tab-lg tab-lifted ${
            location.pathname === "/usuarios" ? "tab-active" : ""
          }  w-1/2`}
          to="/usuarios"
        >
          Lista de Usuarios
        </RouterLink>
        <RouterLink
          className={`tab tab-lg tab-lifted  ${
            location.pathname === "/usuarios/nuevo" ? "tab-active" : ""
          }  w-1/2`}
          to="/usuarios/nuevo"
        >
          Agregar Usuario
        </RouterLink>
      </section>

      <Outlet />
    </>
  );
};
