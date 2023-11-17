import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const OrderDelete = ({ onDelete }) => {
  // const navigate = useNavigate();
  // const onError = (errors, e) => console.log(errors, e);
  // const { handleSubmit } = useForm();
  // const formID = `userForm-${props.orderID}`;
  // const onSubmit = async (data, e) => {
  //   console.dir(await deleteOrder(props.orderID));
  //   navigate(0);
  // };

  const navigate = useNavigate();
  const { handleSubmit } = useForm(); // Use useForm to manage the form state

  const deleteOrder = async () => {
    onDelete();
    try {
      Swal.fire({
        icon: "success",
        title: "Pedido eliminado",
        text: "El pedido se ha eliminado exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          closeModal();
          navigate(0);
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al eliminar el pedido",
        text: "Hubo un problema al eliminar el pedido.",
      });
    }
  };

  const closeModal = () => {
    const modal = document.getElementById("modalOrderBorrar");
    if (modal) {
      modal.close();
    }
  };

  return (
    <dialog id="modalOrderBorrar" className="modal">
      <form onSubmit={handleSubmit(deleteOrder)}>
        <div className="modal-box">
          <h3 className="font-bold text-lg">Desea borrar el pedido?</h3>
          <p className="py-4"></p>
          <section className="flex">
            <button type="submit" className="btn">
              Borrar
            </button>
            <button type="button" onClick={closeModal} className="btn">
              Cerrar
            </button>
            <form
              id="closeModal"
              method="dialog"
              className="modal-backdrop"
            ></form>
          </section>
        </div>
      </form>
    </dialog>
  );
};
