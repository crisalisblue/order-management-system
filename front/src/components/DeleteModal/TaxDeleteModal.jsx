import { TaxDelete } from "../../pages/Impuestos/TaxDelete";

export const TaxDeleteModal = ({ itemID, onDelete }) => {
  const handleDelete = () => {
    onDelete(itemID);
  };

  return (
    <>
      <button className="btn btn-error" onClick={handleDelete}>
        Borrar
      </button>
      <TaxDelete onDelete={onDelete} />
    </>
  );
};
