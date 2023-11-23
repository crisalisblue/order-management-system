import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleTax, getSingleTax } from "../../api/TaxAPI";
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
    <section id="taxForm" className="bg-base-200 prose min-w-full">
      <form
        className="gap-4 p-4 flex flex-col"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div>
          <label htmlFor="name">Nombre:</label>
          <input
            defaultValue={initialValues.name}
            type="text"
            {...register("name")}
          />
        </div>
        <div>
          <label htmlFor="percentage">Porcentaje:</label>
          <input
            defaultValue={initialValues.percentage}
            type="text"
            {...register("percentage")}
          />
        </div>
        <div>
          <label htmlFor="baseAmount">Monto fijo:</label>
          <input
            defaultValue={initialValues.baseAmount}
            type="text"
            {...register("baseAmount")}
          />
        </div>
        <input type="hidden" defaultValue={id} {...register("id")} />
        <button className="btn bg-primary text-base-100" type="submit">
          Modificar
        </button>
      </form>
    </section>
  );
};
