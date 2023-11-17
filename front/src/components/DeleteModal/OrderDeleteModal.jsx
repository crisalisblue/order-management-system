import React from "react";
import { OrderDelete } from "../../pages/Pedidos/OrderDelete";

export const OrderDeleteModal = ({ itemID, onDelete }) => {
  const handleDelete = () => {
    onDelete(itemID);
  };

  return (
    <>
      <button onClick={handleDelete}>🗑</button>
      <OrderDelete onDelete={onDelete} />
    </>
  );
};
