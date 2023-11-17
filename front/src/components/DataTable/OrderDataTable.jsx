import { OrderDeleteModal } from "../DeleteModal/OrderDeleteModal";
export const OrderDataTable = ({ data }) => {
  return (
    <table className={"w-5/6 m-auto text-black"}>
      <thead>
        <tr className={"bg-[#85B7CA]"}>
          <th className={"text-center rounded-tl-md p-1"}>N°Pedido</th>
          <th className={"text-center p-1"}>fecha</th>
          <th className={"text-center p-1"}>Cliente</th>
          <th className={"text-center p-1"}>Total</th>
          <th className={"text-center p-1"}>Baja</th>
          <th className={"text-center rounded-tr-md p-1"}>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {data.length === 0 ? (
          <>
            <tr className={"bg-[#DBE8EC]"}>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
            </tr>
            <tr className={"bg-[#BCDEEB]"}>
              <td className={"text-center rounded-bl-md"}>-</td>
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
                {item.numPedido}
              </td>
              <td className={"text-center p-1"}>{item.fecha}</td>
              <td className={"text-center p-1"}>{item.Cliente}</td>
              <td className={"text-center p-1"}>{item.Total}</td>
              <td className={"text-center p-1"}>{item.Baja ? "no" : "si"}</td>
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
                <OrderDeleteModal itemId={item.id}></OrderDeleteModal>
              </td>
            </tr>
          ))
        )}
      </tbody>
    </table>
  );
};
