import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const TaxDelete = ({ onDelete }) => {
  const navigate = useNavigate();
  const { handleSubmit } = useForm(); // Use useForm to manage the form state

  const deleteTax = async () => {
    onDelete();
    try {
      Swal.fire({
        icon: "success",
        title: "Impuesto eliminado",
        text: "El impuesto  se ha eliminado exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          closeModal();
          navigate(0);
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al eliminar impuesto",
        text: "Hubo un problema al eliminar el impuesto.",
      });
    }
  };

  const closeModal = () => {
    const modal = document.getElementById("modalBorrar");
    if (modal) {
      modal.close();
    }
  };

  return (
    <dialog id="modalBorrar" className="modal">
      <form onSubmit={handleSubmit(deleteTax)}>
        <div className="modal-box">
          <h3 className="font-bold text-lg">¿Desea borrar el registro?</h3>
          <p className="py-4"></p>
          <section className="flex">
            <button type="submit" className="btn">
              Borrar
            </button>
            <button type="button" onClick={closeModal} className="btn">
              Cerrar
            </button>
          </section>
        </div>
      </form>
    </dialog>
  );
};
