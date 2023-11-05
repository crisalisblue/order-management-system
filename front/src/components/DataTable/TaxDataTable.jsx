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
              timer: 1500,
            });

            Toast.fire({
              icon: "success",
              title: "El impuesto ha sido eliminado",
            });
            setTimeout(() => {
              navigate(0);
            }, 1500);
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
    <section className="">
      <table className="w-5/6 m-auto text-black">
        <thead>
          <tr className="bg-[#85B7CA] text-primary border-gray-500">
            <th className="text-center rounded-tl-md p-1">ID</th>
            <th className="text-center p-1">Nombre</th>
            <th className="text-center p-1">Porcentaje</th>
            <th className="text-center p-1">Monto fijo</th>
            <th className="text-center rounded-tr-md p-1">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr
              className={`${
                index % 2 === 0 ? "bg-[#BCDEEB]" : "bg-[#DBE8EC]"
              } drop-shadow-md p-1`}
              key={index}
            >
              <td
                className={`text-center ${
                  index === data.length - 1 ? "rounded-bl-md" : ""
                } p-1`}
              >
                {item.id}
              </td>
              <td className="text-center p-1">{item.name}</td>
              <td className="text-center p-1">{item.percentage}</td>
              <td className="text-center p-1">{item.fixedAmount}</td>
              <td
                className={`text-center flex justify-evenly${
                  index === data.length - 1 ? "rounded-br-md justify-evenly" : ""
                } p-1`}
              >
                <Link to={`/impuestos/${item.id}/editar`}>
                  <button className="btn btn-accent">Editar</button>
                </Link>
                <TaxDeleteModal
                  itemID={item.id}
                  onDelete={() => handleDelete(item.id)}
                ></TaxDeleteModal>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};
