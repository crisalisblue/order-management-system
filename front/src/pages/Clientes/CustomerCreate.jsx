import { createSingleCustomer } from "../../api/customerAPI";
import { useState } from "react";
import { Link as RouterLink } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const CustomerCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, reset, control, setValue } = useForm();
  const [type, setType] = useState("");

  const onSubmit = async (data, e) => {
    try {
      await createSingleCustomer(data);
      Swal.fire({
        icon: "success",
        title: "Cliente creado",
        text: "El cliente se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/clientes");
        }
      });
    } catch (error) {
      console.error(error);
      Swal.fire({
        icon: "error",
        title: "Error al crear cliente",
        text: "Hubo un problema al crear el cliente.",
      });
    }
  };

  const onError = (errors, e) => console.log(errors, e);

  return (
    <>
      <section className="w-5/6 flex justify-end m-auto text-black">
        <RouterLink to="/clientes">
          <button className="m-1 float-right bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow">
            Lista de Clientes
          </button>
        </RouterLink>
      </section>

      <section className="w-5/6 p-5 m-auto bg-[#F1F1F1]">
        <section className="flex justify-center items-center gap-3 flex-col">
          <form
            className="flex w-42 justify-evenly items-center gap-3 my-20 flex-col text-black"
            onSubmit={handleSubmit(onSubmit, onError)}
          >
            <section className="flex w-44 justify-evenly items-end gap-3 flex-col">
              <article className="flex gap-3">
                <label className="text-black">Tipo:</label>
                <select
                  {...register("type")}
                  onChange={(e) => setType(e.target.value)}
                >
                  <option value="">Seleccionar Tipo</option>
                  <option value="PER">PER</option>
                  <option value="BUS">BUS</option>
                </select>
              </article>
              {type === "PER" && (
                <>
                  <article className="flex gap-3">
                    <label className="text-black">Nombre:</label>
                    <input type="text" {...register("name")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Apellido:</label>
                    <input type="text" {...register("lastName")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Direccion:</label>
                    <input type="text" {...register("address")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">DNI:</label>
                    <input type="text" {...register("dni")} />
                  </article>
                </>
              )}
              {type === "BUS" && (
                <>
                  <article className="flex gap-3">
                    <label className="text-black">Nombre Empresa:</label>
                    <input type="text" {...register("businessName")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Cuit:</label>
                    <input type="text" {...register("cuit")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Inicio actividades:</label>
                    <input type="date" {...register("activityStartDate")} />
                  </article>
                  <h1 className="text-lg text-black font-semibold underline self-center">
                    Contacto:
                  </h1>
                  <article className="flex gap-3">
                    <label className="text-black">Nombre:</label>
                    <input type="text" {...register("name")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Apellido:</label>
                    <input type="text" {...register("lastName")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Direccion:</label>
                    <input type="text" {...register("address")} />
                  </article>
                  <article className="flex gap-3">
                    <label className="text-black">Dni:</label>
                    <input type="text" {...register("dni")} />
                  </article>
                </>
              )}
            </section>
            <button
              className="float-right bg-primary text-base-100 hover:bg-gray-100  font-semibold py-1 px-2 border border-gray-400 rounded shadow"
              type="submit"
            >
              Agregar
            </button>
          </form>
        </section>
      </section>
    </>
  );
};
