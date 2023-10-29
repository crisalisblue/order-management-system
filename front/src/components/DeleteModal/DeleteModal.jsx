import React from "react";
import { UserDelete } from "../../pages/Usuarios/UserDelete";

export const DeleteModal = ({ itemId }) => {
  return (
    <>
      <button
        className="btn btn-error"
        onClick={() => document.getElementById("modalBorrar").showModal()}
      >
        Borrar
      </button>
      <UserDelete userID={itemId}></UserDelete>
    </>
  );
};
