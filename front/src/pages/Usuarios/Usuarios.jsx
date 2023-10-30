import { Outlet } from "react-router-dom";
import { DynamicLink } from "../../components/DynamicLink/DynamicLink";

export const Usuarios = () => {
  return (
    <>
      <section className="tabs bg-primary">
        <DynamicLink
          name="Lista de Usuarios"
          expectedUrl="/usuarios"
        ></DynamicLink>
        <DynamicLink
          name="Crear Usuario"
          expectedUrl="/usuarios/nuevo"
        ></DynamicLink>
      </section>
      <Outlet />
    </>
  );
};
