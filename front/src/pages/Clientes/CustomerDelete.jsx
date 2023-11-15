import { useForm } from "react-hook-form";
import { deleteSingleCustomer } from "../../api/customerAPI";
import { useNavigate } from "react-router";

export const CustomerDelete = (props) => {
  const navigate = useNavigate();
  const onError = (errors, e) => console.log(errors, e);
  const { handleSubmit } = useForm();
  const formID = `userForm-${props.customerID}`;
  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleCustomer(props.customerID));
    navigate(0);
  };
  return (
    <dialog id="modalCustomerBorrar" className="modal">
      <form id={formID} onSubmit={handleSubmit(onSubmit, onError)}>
        <div className="modal-box">
          <h3 className="font-bold text-lg">Desea borrar el registro?</h3>
          <p className="py-4"></p>
          <section className="flex">
            <button type="submit" className="btn">
              Borrar
            </button>
            <button type="submit" form="closeModal" className="btn">
              close
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