import { Outlet, Link as RouterLink, useLocation } from "react-router-dom";

export const Pedidos = () => {
  const location = useLocation();

  return (
    <article className="prose grid min-w-full">
      <h1 className="text-primary text-center m-0 p-0">Pedidos</h1>
      {location.pathname === "/pedidos" && (
        <RouterLink
          to="/pedidos/nuevo"
          className={`w-5/6 m-auto  ${
            location.pathname === "/pedidos/nuevo" ? "tab-active" : ""
          } w-1/2`}
        >
          <button className="float-right bg-primary text-base-100 hover:bg-gray-100 font-semibold py-1 px-2 border border-gray-400 rounded shadow">
            Agregar Pedido
          </button>
        </RouterLink>
      )}
      <Outlet />
    </article>
  );
};
