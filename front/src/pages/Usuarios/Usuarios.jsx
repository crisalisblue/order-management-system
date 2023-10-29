import { Outlet } from "react-router-dom";

export const Usuarios = () => {
  return (
    <>
      <section className="prose overflow-auto max-h-[80dvh] min-w-full">
        <h1 className="text-primary text-center  m-0 p-0">Usuarios</h1>
        <Outlet />
      </section>
    </>
  );
};
