import { useForm } from "react-hook-form";
import { deleteSingleUser } from "../../api/UserAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const UserDelete = (props) => {
  const navigate = useNavigate();
  const { handleSubmit } = useForm();
  const formID = `userForm-${props.userID}`;

  const onDelete = async () => {
    try {
      await deleteSingleUser(props.userID);
      closeModal();
      Swal.fire({
        icon: "success",
        title: "Usuario eliminado",
        text: "El usuario se ha eliminado exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          closeModal();
          navigate(0);
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al eliminar usuario",
        text: "Hubo un problema al eliminar el usuario.",
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
      <form id={formID} onSubmit={handleSubmit(onDelete)}>
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
