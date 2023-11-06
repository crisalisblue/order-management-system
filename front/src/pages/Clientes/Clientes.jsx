import { Outlet } from "react-router-dom";
import { Link as RouterLink, useLocation } from "react-router-dom";
//import Style from './Cliente.css'

export const Clientes = () => {
  const location = useLocation();
  return (
    <>
    <h1 className={"text-4xl m-auto text-black text-center"}> Clientes</h1>
    
      <section className={" w-5/6 p-2 m-auto text-black"}>
        {
          location.pathname === "/clientes" ?
          <section className="flex gap-2 justify-between items-center">
            <h1>Filtros:</h1>
            <input className="h-7 p-1" type="text" placeholder="nombre"/>
            <input className="h-7 p-1" type="text" placeholder="documento" />
            <input className="h-7 p-1" type="text" placeholder="tipo"/>
          <button className={"btn text-white bg-[#001F3D]"}>
            <RouterLink
          className={` ${
            location.pathname === "/clientes/nuevo" ? "tab-active" : ""
          }  w-1/2`}
          to="/clientes/nuevo"
        >
          Agregar Cliente
        </RouterLink>
          </button>
          </section>
        :
        ""
        }
        
      </section>

      <Outlet />
    </>
  );
};
