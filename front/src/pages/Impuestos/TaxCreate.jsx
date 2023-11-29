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
    <section id="taxCreate" className="w-5/6 prose min-w-full">
      <form
        className="w-5/6 mx-auto p-6 bg-white rounded-md shadow-md"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div className="mb-4">
          <label
            htmlFor="name"
            className="block text-sm font-semibold text-gray-600"
          >
            Nombre:
          </label>
          <input
            type="text"
            id="name"
            {...register("name")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="percentage"
            className="block text-sm font-semibold text-gray-600"
          >
            Porcentaje:
          </label>
          <input
            type="text"
            id="percentage"
            {...register("percentage")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="baseAmount"
            className="block text-sm font-semibold text-gray-600"
          >
            Monto fijo:
          </label>
          <input
            type="text"
            id="baseAmount"
            {...register("baseAmount")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <button
          type="submit"
          className="w-1/3 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Agregar Impuesto
        </button>
      </form>
    </section>
  );
};
