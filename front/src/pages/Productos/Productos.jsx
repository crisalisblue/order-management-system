import "./Productos.css";
import {Outlet} from "react-router";
import {DynamicLink} from "../../components/DynamicLink/DynamicLink.jsx";

export const Productos = () => {
  return (
      <>
        <DynamicLink
            name="Lista de Productos"
            expectedUrl="/productos"
        ></DynamicLink>
        <DynamicLink
            name="Agregar Producto"
            expectedUrl="/productos/nuevo"
        ></DynamicLink>
        <Outlet />
      </>
  );
};
