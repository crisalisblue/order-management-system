import { OrderDeleteModal } from "../DeleteModal/OrderDeleteModal";
import { Link } from "react-router-dom";
import { deleteOrder } from "../../api/orderAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const OrderDataTable = ({ data }) => {
  const navigate = useNavigate();
  const handleDelete = (id) => {
    Swal.fire({
      title: "¿Estás seguro?",
      text: "Una vez eliminado, no podrás recuperar este pedido",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteOrder(id)
          .then(() => {
            const Toast = Swal.mixin({
              toast: true,
              position: "top",
              showConfirmButton: false,
              timer: 1500,
            });

            Toast.fire({
              icon: "success",
              title: "El pedido ha sido eliminado",
            });
            setTimeout(() => {
              navigate(0);
            }, 1500);
          })
          .catch((error) => {
            Swal.fire(
              "Error",
              "Hubo un problema al eliminar el pedido",
              "error"
            );
            console.error("Error al eliminar el pedido:", error);
          });
      }
    });
  };
  // Verificar si no hay datos o keysToShow
  if (!data || data.length === 0) {
    return <div>No hay datos para mostrar.</div>;
  }
  return (
    <table className={"w-5/6 m-auto text-black"}>
      <thead>
        <tr className={"bg-[#85B7CA]"}>
          <th className={"text-center rounded-tl-md p-1"}>N°Pedido</th>
          <th className={"text-center p-1"}>Fecha</th>
          <th className={"text-center p-1"}>Cliente</th>
          <th className={"text-center p-1"}>Total</th>
          <th className={"text-center p-1"}>Activo</th>
          <th className={"text-center rounded-tr-md p-1"}>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {data.length === 0 ? (
          <>
            <tr className={"bg-[#BCDEEB]"}>
              <td className={"text-center rounded-bl-md"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center rounded-br-md"}>-</td>
            </tr>
          </>
        ) : (
          data.map((item, index) => (
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
                {item.idOrder}
              </td>
              <td className={"text-center p-1"}>{item.dateOrder}</td>
              <td className={"text-center p-1"}>{item.customerName}</td>
              <td className={"text-center p-1"}>
                {item.totalPrice ? "$" + item.totalPrice : "$0"}
              </td>
              <td className={"text-center p-1"}>{item.active ? "si" : "no"}</td>
              <td
                className={`text-center ${
                  index === data.length - 1 ? "rounded-br-md" : ""
                } p-1`}
              >
                <a href={`/Pedidos/1/editar`}>
                  <button
                    className={
                      "hover:text-[#008A70] hover:scale-125 duration-300 bolder mx-2"
                    }
                  >
                    ✎
                  </button>
                </a>
                <OrderDeleteModal
                  itemID={item.idOrder}
                  onDelete={() => handleDelete(item.idOrder)}
                ></OrderDeleteModal>
              </td>
            </tr>
          ))
        )}
      </tbody>
    </table>
  );
};
