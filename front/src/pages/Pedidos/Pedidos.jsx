import { Outlet } from "react-router-dom";
import { Link as RouterLink, useLocation } from "react-router-dom";
//import Style from './Pedidos.css'

export const Pedidos = () => {
  const location = useLocation();
  return (
    <>
      <h1 className={"text-4xl m-auto text-black text-center"}> Pedidos</h1>

      <section className={" w-5/6 p-2 m-auto text-black"}>
        {location.pathname === "/pedidos" ? (
          <section className="flex gap-2 justify-between items-center">
            <h1>Filtros:</h1>
            <input className="h-7 p-1" type="text" placeholder="nombre" />
            <input className="h-7 p-1" type="text" placeholder="documento" />
            <input className="h-7 p-1" type="text" placeholder="tipo" />
            <button className={"btn text-white bg-[#001F3D]"}>
              <RouterLink
                className={` ${
                  location.pathname === "/pedidos/nuevo" ? "tab-active" : ""
                }  w-1/2`}
                to="/pedidos/nuevo"
              >
                Agregar Pedido
              </RouterLink>
            </button>
          </section>
        ) : (
          ""
        )}
      </section>

      <Outlet />
    </>
  );
};
