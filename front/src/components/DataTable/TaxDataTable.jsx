import { TaxDeleteModal } from "../DeleteModal/TaxDeleteModal";
import { Link } from "react-router-dom";
import { deleteSingleTax } from "../../api/TaxAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const TaxDataTable = ({ data }) => {
  const navigate = useNavigate();
  const handleDelete = (id) => {
    Swal.fire({
      title: "¿Estás seguro?",
      text: "Una vez eliminado, no podrás recuperar este impuesto",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteSingleTax(id)
          .then(() => {
            const Toast = Swal.mixin({
              toast: true,
              position: "top",
              showConfirmButton: false,
              timer: 1500, // Controla la duración del mensaje en milisegundos (en este caso, 1.5 segundos)
            });

            Toast.fire({
              icon: "success",
              title: "El impuesto ha sido eliminado",
            });
            setTimeout(() => {
              navigate(0);
            }, 1500); // Navega a la página '0' después de 1.5 segundos
          })
          .catch((error) => {
            Swal.fire(
              "Error",
              "Hubo un problema al eliminar el impuesto",
              "error"
            );
            console.error("Error al eliminar el impuesto:", error);
          });
      }
    });
  };

  return (
    <section className="bg-base-200 prose overflow-auto max-h-[80dvh] min-w-[98%]">
      <table className="m-0 table table-xs bg-secondary text-primary ">
        <thead className="min-w-full">
          <tr className="bg-secondary text-primary border-gray-500">
            <th>ID</th>
            <th>Nombre</th>
            <th>Porcentaje</th>
            <th>Monto fijo</th>
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
                {item.id}
              </td>
              <td className=" text-center align-middle border-r-2 border-gray-500">
                {item.name}
              </td>
              <td className=" text-center align-middle border-x-2 border-gray-500">
                {item.percentage}
              </td>
              <td className=" text-center align-middle border-x-2 border-gray-500 ">
                {item.fixedAmount}
              </td>
              <td className="flex place-content-evenly">
                <Link to={`/impuestos/${item.id}/editar`}>
                  <button className="btn btn-accent">Editar</button>
                </Link>

                <TaxDeleteModal
                  itemID={item.id}
                  onDelete={() => handleDelete(item.id)} // Pasamos el ID a la función de borrado
                ></TaxDeleteModal>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};
