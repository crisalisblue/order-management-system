import { useForm } from "react-hook-form";
import { deleteSingleSubscription } from "../../api/subscriptionAPI.js";
import { useNavigate } from "react-router";

export const SubscriptionDelete = (props) => {
  const navigate = useNavigate();
  const onError = (errors, e) => console.log(errors, e);
  const { handleSubmit } = useForm();
  const formID = `subscriptionForm-${props.subscriptionID}`;

  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleSubscription(props.subscriptionID));
    navigate(0);
  };

  return (
    <dialog id="modalBorrar" className="modal">
      <form id={formID} onSubmit={handleSubmit(onSubmit, onError)}>
        <div className="modal-box">
          <h3 className="font-bold text-lg">¿Desea borrar el registro?</h3>
          <p className="py-4"></p>
          <section className="flex">
            <button type="submit" className="btn">
              Borrar
            </button>
            <button type="submit" form="closeModal" className="btn">
              Cerrar
            </button>
          </section>
        </div>
      </form>
    </dialog>
  );
};
