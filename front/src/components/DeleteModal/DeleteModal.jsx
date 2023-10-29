import React from "react";
import { UserDelete } from "../../pages/Usuarios/UserDelete";

export const DeleteModal = ({ itemId }) => {
  console.dir(itemId);
  return (
    <>
      <button
        className="btn"
        onClick={() => document.getElementById("modalBorrar").showModal()}
      >
        Borrar
      </button>
      <UserDelete userID={itemId}></UserDelete>
    </>
  );
};
