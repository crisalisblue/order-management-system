import { DeleteModal } from "../DeleteModal/DeleteModal";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const DataTable = ({
  data,
  keysToShow,
  itemName,
  editPath,
  deleteFunction,
}) => {
  const navigate = useNavigate();
  const handleDelete = (id) => {
    Swal.fire({
      title: `¿Estás seguro de eliminar este ${itemName}?`,
      text: `Una vez eliminado, no podrás recuperar este ${itemName}`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: `Sí, eliminar este ${itemName}`,
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteFunction(id)
          .then(() => {
            const Toast = Swal.mixin({
              toast: true,
              position: "top",
              showConfirmButton: false,
              timer: 1500,
            });

            Toast.fire({
              icon: "success",
              title: `El ${itemName} ha sido eliminado`,
            });
            setTimeout(() => {
              navigate(0);
            }, 1500);
          })
          .catch((error) => {
            Swal.fire(
              "Error",
              `Hubo un problema al eliminar el ${itemName}`,
              "error"
            );
            console.error(`Error al eliminar el ${itemName}:`, error);
          });
      }
    });
  };
console.dir(keysToShow)
  return (
    <section className="overflow-y-auto overflow-x-hidden">
      <table className={"w-5/6 m-auto text-black"}>
        <thead className="min-w-full">
          <tr className={"bg-[#85B7CA] text-primary border-gray-500"}>
            {keysToShow.map((key, index) => (
              <th
                key={index}
                className={`text-center ${index === 0 ? "rounded-tl-md" : ""} ${
                  index === keysToShow.length - 1 ? "rounded-tr-md" : ""
                } p-1`}
              >
                {key}
              </th>
            ))}
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
              {keysToShow.map((key, idx) => (
                <td
                  key={idx}
                  className={`text-center ${
                    index === data.length - 1 && idx === keysToShow.length - 1
                      ? "rounded-br-md"
                      : ""
                  } p-1`}
                >
                  {item[key]}
                </td>
              ))}
              <td
                className={`text-center flex justify-evenly ${
                  index === data.length - 1 ? "rounded-br-md" : ""
                } p-1`}
              >
                <Link to={`${editPath}/${item.id}/editar`}>🛠</Link>
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
