import { Outlet } from "react-router-dom";
import { Link, useLocation } from "react-router-dom";
//import Style from './Cliente.css'

export const Clientes = () => {
  const location = useLocation();
  return (
    <>
      <article className="prose min-w-full">
        <h1 className="text-primary text-center m-0 p-0">Clientes</h1>

        <section className={" w-5/6 p-2 m-1 text-black"}>
          {location.pathname === "/clientes" ? (
            <section className="flex gap-2 justify-between items-center">
              <label htmlFor="filter1" className="mr-4">
                Filtrar:
              </label>
              <input className="h-7 p-1" type="text" placeholder="nombre" />
              <input className="h-7 p-1" type="text" placeholder="documento" />
              <input className="h-7 p-1" type="text" placeholder="tipo" />
              <Link
                className={` ${
                  location.pathname === "/clientes/nuevo" ? "tab-active" : ""
                }  w-1/2`}
                to="/clientes/nuevo"
              >
                <button className="float-right bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow">
                  Agregar Cliente
                </button>
              </Link>
            </section>
          ) : (
            ""
          )}
        </section>
      </article>
      <Outlet />
    </>
  );
};
