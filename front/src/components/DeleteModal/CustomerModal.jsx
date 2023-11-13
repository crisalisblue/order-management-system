import React from "react";
import { CustomerDelete } from "../../pages/Clientes/CustomerDelete";

export const CustomerModal = ({ itemId }) => {
  console.dir(itemId);
  return (
    <>
      <button
        className={"hover:text-red-700 hover:scale-125 duration-300 bolder mx-2"}
        onClick={() => document.getElementById("modalCustomerBorrar").showModal()}
      >
       🗑
      </button>
      <CustomerDelete customerID={itemId}>🗑</CustomerDelete>
    </>
  );
};