import { createSingleTax } from "../../api/TaxAPI.js";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const TaxCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    try {
      await createSingleTax(data);
      Swal.fire({
        icon: "success",
        title: "Impuesto creado",
        text: "El impuesto se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/impuestos");
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al crear impuesto",
        text: "Hubo un problema al crear el impuesto.",
      });
    }
  };

  return (
    <section id="userForm" className="bg-base-200 prose min-w-full">
      <form
        className="gap-4 p-4 flex flex-col"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div>
          <label htmlFor="name">Nombre:</label>
          <input type="text" {...register("name")} />
        </div>

        <div>
          <label htmlFor="username">Porcentaje:</label>
          <input type="text" {...register("percentage")} />
        </div>

        <div>
          <label htmlFor="password">Monto fijo:</label>
          <input type="text" {...register("baseAmount")} />
        </div>

        <button className="btn bg-primary text-base-100" type="submit">
          Agregar
        </button>
      </form>
    </section>
  );
};
