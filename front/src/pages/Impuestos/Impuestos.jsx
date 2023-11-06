import { Outlet } from "react-router-dom";
import { Link, useLocation } from "react-router-dom";
export const Taxes = () => {
  const { pathname } = useLocation();
  return (
    <>
      <article className="prose min-w-full">
        <h1 className="text-primary text-center m-0 p-0">Impuestos</h1>

        <section id="actions" className="flex items-center ">
          {/* <section
            id="filters"
            className={`${
              pathname == "/tax"
                ? `flex items-center justify-between m-4  gap-4`
                : "hidden"
            } `}
          >
            <label htmlFor="filter1" className="mr-4">
              Filtrar:
            </label>
            <input
              type="search"
              name=""
              id=""
              className="pl-1"
              placeholder="usuario"
            />
            <input
              type="search"
              name=""
              id=""
              className="pl-1"
              placeholder="nombre"
            />
            
          </section> */}
          <Link to={pathname === `/impuestos` ? `/impuestos/nuevo` : `/impuestos`}>
            <button className="float-right bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow">
              {pathname === `/impuestos`
                ? `+ Agregar Nuevo`
                : `Lista de Impuestos`}
            </button>
          </Link>
        </section>
      </article>

      <Outlet />
    </>
  );
};
