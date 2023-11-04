import { DeleteModal } from "../DeleteModal/DeleteModal";
import { Link } from "react-router-dom";
import { deleteSingleUser } from "../../api/UserAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const DataTable = ({ data }) => {
  const navigate = useNavigate();
  const handleDelete = (id) => {
    Swal.fire({
      title: "¿Estás seguro?",
      text: "Una vez eliminado, no podrás recuperar este usuario",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteSingleUser(id)
          .then(() => {
            const Toast = Swal.mixin({
              toast: true,
              position: "top",
              showConfirmButton: false,
              timer: 1500,
            });

            Toast.fire({
              icon: "success",
              title: "El usuario ha sido eliminado",
            });
            setTimeout(() => {
              navigate(0);
            }, 1500);
          })
          .catch((error) => {
            Swal.fire(
              "Error",
              "Hubo un problema al eliminar el usuario",
              "error"
            );
            console.error("Error al eliminar el usuario:", error);
          });
      }
    });
  };

  return (
    <section className="">
      <table className={"w-5/6 m-auto text-black"}>
        <thead className="min-w-full">
          <tr className={"bg-[#85B7CA] text-primary border-gray-500"}>
            <th className={"text-center rounded-tl-md p-1"}>Usuario</th>
            <th className={"text-center p-1"}>Nombre</th>

            <th className={"text-center rounded-tr-md p-1"}>Acciones</th>
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
                {item.username}
              </td>
              <td className="text-center p-1">{item.name}</td>

              <td
                className={`text-center flex justify-evenly ${
                  index === data.length - 1 ? "rounded-br-md" : ""
                } p-1`}
              >
                <Link to={`/usuarios/${item.id}/editar`}>
                  <button className="btn btn-accent">Editar</button>
                </Link>
                <DeleteModal
                  itemID={item.id}
                  onDelete={() => handleDelete(item.id)}
                ></DeleteModal>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};
