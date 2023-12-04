import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleTax, getSingleTax } from "../../api/TaxAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const TaxUpdate = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [tax, setTax] = useState(null);
  const [initialValues, setInitialValues] = useState({
    name: "",
    percentage: "",
    fixedAmount: "",
  });

  useEffect(() => {
    const fetchTaxData = async () => {
      try {
        const taxData = await getSingleTax(id);
        setTax(taxData);
        setInitialValues({
          name: taxData.name,
          percentage: taxData.percentage,
          baseAmount: taxData.baseAmount,
        });
      } catch (error) {
        console.error("Error fetching tax data:", error);
      }
    };

    fetchTaxData();
  }, [id]);

  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    data.id = id; // Agrega el ID al objeto de datos antes de enviarlo
    await updateSingleTax(data);
    showSuccessAlert();
    navigate("/impuestos");
  };

  const showSuccessAlert = () => {
    Swal.fire({
      icon: "success",
      title: "Impuesto actualizado",
      showConfirmButton: false,
      timer: 1500,
    });
  };

  if (!tax) {
    return <div>Cargando...</div>;
  }

  return (
    <section id="taxForm" className="w-5/6 prose min-w-full">
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
            defaultValue={initialValues.name}
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
            defaultValue={initialValues.percentage}
            {...register("percentage")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <input type="hidden" defaultValue={id} {...register("id")} />

        <button
          type="submit"
          className="w-1/3 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Modificar Impuesto
        </button>
      </form>
    </section>
  );
};
