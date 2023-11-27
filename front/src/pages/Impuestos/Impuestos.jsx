import { Outlet } from "react-router-dom";
import { Link as RouterLink, useLocation } from "react-router-dom";
export const Taxes = () => {
  const { pathname } = useLocation();
  return (
    <>
      <article className="prose grid min-w-full">
        <h1 className="text-primary text-center m-0 p-0">Impuestos</h1>
        <section id="actions" className="flex items-center ">
          <RouterLink
            to={pathname === `/impuestos` ? `/impuestos/nuevo` : `/impuestos`}
            className={`w-5/6 m-auto  ${
              location.pathname === "/impuestos/nuevo" ? "tab-active" : ""
            } w-1/2`}
          >
            <button className="float-right bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow">
              {pathname === `/impuestos`
                ? `+ Agregar Impuesto`
                : `Lista de Impuestos`}
            </button>
          </RouterLink>
        </section>
      </article>

      <Outlet />
    </>
  );
};
