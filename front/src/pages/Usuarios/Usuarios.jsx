import { Outlet } from "react-router-dom";

export const Usuarios = () => {
  return (
    <>
      <article className="bg-gray-500 prose min-w-full">
        <h1 className="text-primary text-center m-0 p-0">Usuarios</h1>
        <section id="actions" className="flex items-center gap-4 m-4">
          <button class="bg-white hover:bg-gray-100 text-gray-800 font-semibold py-1 px-2 border border-gray-400 rounded shadow">
            + Agregar Nuevo
          </button>
        </section>
        <section id="filters" className="flex items-center gap-4">
          <label htmlFor="filter1" className="mr-4">
            Filtrar Por:
          </label>
          <select
            id="filter1"
            className="select select-xs select-bordered max-w-xs"
          >
            <option disabled selected>
              Filtros 1
            </option>
            <option>Han Solo</option>
            <option>Greedo</option>
          </select>
          <select className="select select-xs select-bordered max-w-xs">
            <option disabled selected>
              Filtros 2
            </option>
            <option>Han Solo</option>
            <option>Greedo</option>
          </select>
          <select className="select select-xs select-bordered max-w-xs">
            <option disabled selected>
              Filtros 3
            </option>
            <option>Han Solo</option>
            <option>Greedo</option>
          </select>
          <select className="select select-xs select-bordered max-w-xs">
            <option disabled selected>
              Filtros 4
            </option>
            <option>Han Solo</option>
            <option>Greedo</option>
          </select>
        </section>
      </article>
      <section className="prose overflow-auto max-h-[80dvh] min-w-full">
        <Outlet />
      </section>
    </>
  );
};
