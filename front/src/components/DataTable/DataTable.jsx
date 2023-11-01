import { DeleteModal } from "../DeleteModal/DeleteModal";
import { Link } from "react-router-dom";
export const DataTable = ({ data }) => {
  return (
    <section className="bg-base-200 prose overflow-auto max-h-[80dvh] min-w-[98%]">
      <table className="m-0 table table-xs bg-secondary text-primary ">
        <thead className="min-w-full">
          <tr className="bg-secondary text-primary border-gray-500">
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Contraseña</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr
              className="text-accent odd:bg-base-200 even:bg-base-100"
              key={index}
            >
              <td className=" text-center align-middle border-r-2 border-gray-500">
                {item.username}
              </td>
              <td className=" text-center align-middle border-x-2 border-gray-500">
                {item.name}
              </td>
              <td className=" text-center align-middle border-x-2 border-gray-500 ">
                {item.password}
              </td>
              <td className="flex place-content-evenly">
                <Link to={`/usuarios/${item.id}/editar`}>
                  <button className="btn btn-accent">Editar</button>
                </Link>

                <DeleteModal itemId={item.id}></DeleteModal>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};
