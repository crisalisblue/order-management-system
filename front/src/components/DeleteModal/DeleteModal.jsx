import { UserDelete } from "../../pages/Usuarios/UserDelete";

export const DeleteModal = ({ itemID, onDelete }) => {
  const handleDelete = () => {
    onDelete(itemID);
  };

  return (
    <>
      <button className="btn btn-error" onClick={handleDelete}>
        Borrar
      </button>
      <UserDelete onDelete={onDelete} />
    </>
  );
};
