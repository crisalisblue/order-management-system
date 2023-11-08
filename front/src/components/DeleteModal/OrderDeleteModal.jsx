import React from "react";
import { OrderDelete } from "../../pages/Pedidos/OrderDelete";

export const OrderDeleteModal = ({ itemId }) => {
  console.dir(itemId);
  return (
    <>
      <button
        className={
          "hover:text-red-700 hover:scale-125 duration-300 bolder mx-2"
        }
        onClick={() =>
          document.getElementById("modalCustomerBorrar").showModal()
        }
      >
        🗑
      </button>
      <OrderDelete orderID={itemId}>🗑</OrderDelete>
    </>
  );
};
