import { useForm } from "react-hook-form";
import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  getSingleCustomer,
  updateSingleCustomer,
} from "../../api/customerAPI.js";
import Swal from "sweetalert2";

export const CustomerUpdate = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { register, handleSubmit, setValue } = useForm({
    defaultValues: {
      id: Number(id),
    },
  });
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    const fetchCustomer = async () => {
      try {
        const data = await getSingleCustomer(id);
        setCustomer(data);

        setValue("type", data.type);
        setValue("name", data.name);
        setValue("lastName", data.lastName);  
        setValue("dni", data.dni);
        setValue("businessName", data.businessName);
        setValue("cuit", data.cuit);
        setValue("activityStartDate", data.activityStartDate);
        setValue("address", data.address);
      } catch (error) {
        console.error("Error fetching customer data:", error);
        showErrorAlert();
      }
    };

    fetchCustomer();
  }, [id, setValue]);

  const onSubmit = async (data) => {
    try {
      data.id = id; // Agrega el ID al objeto de datos antes de enviarlo
      await updateSingleCustomer(data);
      showSuccessAlert();
      navigate("/clientes");
    } catch (error) {
      console.error("Error updating customer:", error);
      showErrorAlert();
    }
  };

  const showSuccessAlert = () => {
    Swal.fire({
      icon: "success",
      title: "Cliente actualizado",
      showConfirmButton: false,
      timer: 1500,
    });
  };

  const showErrorAlert = () => {
    Swal.fire({
      icon: "error",
      title: "Error",
      text: "Hubo un problema al actualizar el cliente",
    });
  };

  if (!customer) {
    return <div>Cargando...</div>;
  }

  // Renderiza los campos comunes y específicos según el tipo de cliente
  const renderCustomerFields = () => {
    return (
      <>
        <div className="flex w-44 justify-evenly items-end gap-3 flex-col">
          <div className="flex gap-3">
            <label className="text-black">Tipo:</label>
            <input type="text" value={customer.type} {...register("type")} />
          </div>
          <div className="flex gap-3">
            <label className="text-black">Nombre:</label>
            <input type="text" {...register("name")} />
          </div>
          <div className="flex gap-3">
            <label className="text-black">Direccion:</label>
            <input type="text" {...register("address")} />
          </div>
          <div className="flex gap-3">
            <label className="text-black">Apellido:</label>
            <input type="text" {...register("lastName")} />
          </div>
          <div className="flex gap-3">
            <label className="text-black">DNI:</label>
            <input type="text" {...register("dni")} />
          </div>
        </div>

        {customer.type === "BUS" && (
          <div className="flex gap-3">
            <label className="text-black">Nombre Empresa:</label>
            <input type="text" {...register("businessName")} />
          </div>
        )}

        {customer.type === "BUS" && (
          <div className="flex gap-3">
            <label className="text-black">Cuit:</label>
            <input type="text" {...register("cuit")} />
          </div>
        )}

        {customer.type === "BUS" && (
          <div className="flex gap-3">
            <label className="text-black">Inicio actividades:</label>
            <input type="date" {...register("activityStartDate")} />
          </div>
        )}

        <button
          className="self-center btn my-2 text-white bg-[#001F3D]"
          type="submit"
        >
          Cambiar
        </button>
      </>
    );
  };

  return (
    <>
      <section className="w-5/6 flex p-2 justify-end m-auto text-black">
        <Link to="/clientes">
          <button className="btn my-2 text-white bg-[#001F3D]">
            Lista de Clientes
          </button>
        </Link>
      </section>

      <section className="w-5/6 p-5 m-auto bg-[#F1F1F1]">
        <section className="flex justify-center items-center gap-3  flex-col">
          <form
            className="flex justify-center text-black items-end gap-3 my-10  flex-col"
            onSubmit={handleSubmit(onSubmit)}
          >
            {renderCustomerFields()}
          </form>
        </section>
      </section>
    </>
  );
};
