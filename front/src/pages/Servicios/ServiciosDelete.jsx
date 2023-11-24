import { useForm } from "react-hook-form";
import { deleteSingleService } from "../../api/serviceAPI.js";
import { useNavigate } from "react-router-dom";

export const ServiciosDelete = (props) => {
  const navigate = useNavigate();
  const { handleSubmit } = useForm();
  const onError = (errors, e) => console.log(errors, e);

  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleService(props.serviceID));
    navigate(0);
  };

  return (
    <dialog id="modalBorrar" className="modal">
      <form onSubmit={handleSubmit(onSubmit, onError)}>
        <div className="modal-box">
          <h3 className="font-bold text-lg">¿Desea borrar el registro?</h3>{" "}
          <p className="py-4"></p>
          <section className="flex">
            <button type="submit" className="btn">
              Borrar
            </button>
            <button type="button" form="closeModal" className="btn">
              Cerrar
            </button>
          </section>
        </div>
      </form>
    </dialog>
  );
};
