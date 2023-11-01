import { Outlet } from "react-router-dom";
import { Link, useLocation } from "react-router-dom";
export const Usuarios = () => {
  const { pathname } = useLocation();
  console.dir(pathname);
  return (
    <>
      <article className="height-full prose min-w-full">
        <h1 className="text-primary text-center m-0 p-0">Usuarios</h1>
        <section id="actions" className="flex items-center justify-between m-4">
          <section id="filters" className="m-4 flex items-center gap-4">
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
            <label htmlFor="">Estado:</label>
            <select className=" select-xs select-bordered max-w-xs">
              <option>Todos</option>
              <option>Activos</option>
              <option>Inactivos</option>
            </select>
          </section>
          <Link to={pathname === `/usuarios` ? `/usuarios/nuevo` : `/usuarios`}>
            <button className="bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow">
              {pathname === `/usuarios`
                ? `+ Agregar Nuevo`
                : `Lista de Usuarios`}
            </button>
          </Link>
        </section>
      </article>

      <Outlet />
    </>
  );
};
