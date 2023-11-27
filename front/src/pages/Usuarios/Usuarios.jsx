import { Outlet, Link as RouterLink, useLocation } from "react-router-dom";

export const Usuarios = () => {
  const { pathname } = useLocation();

  return (
    <article className="prose grid min-w-full">
      <h1 className="text-primary text-center m-0 p-0">Usuarios</h1>

      <RouterLink
        to={pathname === "/usuarios" ? "/usuarios/nuevo" : "/usuarios"}
        className={`w-5/6 m-auto  ${
          location.pathname === "/pedidos/nuevo" ? "tab-active" : ""
        } w-1/2`}
      >
        <button className="float-right bg-primary text-base-100 hover:bg-gray-100 font-semibold py-1 px-2 border border-gray-400 rounded shadow">
          {pathname === "/usuarios" ? "+ Agregar Usuario" : "Lista de Usuarios"}
        </button>
      </RouterLink>

      <Outlet />
    </article>
  );
};
