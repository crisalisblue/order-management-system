import { createSingleCustomer } from "../../api/customerAPI.js";
import { useState } from "react";
import { Link as RouterLink } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const CustomerCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, control, setValue } = useForm();
  const [type, setType] = useState("");

  const onSubmit = async (data) => {
    try {
      await createSingleCustomer(data);
      Swal.fire({
        icon: "success",
        title: "Cliente creado",
        text: "El cliente se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate(-1);
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

  return (
    <>
      <section className="w-5/6 flex justify-end m-auto text-black">
        <RouterLink to="/clientes">
          <button className="float-right bg-primary text-base-100 hover:bg-gray-100 font-semibold py-1 px-2 border border-gray-400 rounded shadow">
            Lista de Clientes
          </button>
        </RouterLink>
      </section>

      <section className="w-5/6 p-5 m-auto bg-[#F1F1F1]">
        <section className="flex justify-center items-center gap-3 flex-col">
          <form
            className="flex w-42 justify-evenly items-center gap-3 my-20 flex-col text-black"
            onSubmit={handleSubmit(onSubmit)}
          >
            <section className="flex w-44 justify-evenly items-end gap-3 flex-col">
              <article className="flex gap-3 items-center">
                <label className="text-black">Tipo:</label>
                <select
                  {...register("type")}
                  onChange={(e) => setType(e.target.value)}
                  className="border border-gray-400 rounded px-2 py-1"
                >
                  <option value="">Seleccionar Tipo</option>
                  <option value="PER">Persona</option>
                  <option value="BUS">Empresa</option>
                </select>
              </article>

              {type === "PER" && (
                <>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Nombre:</label>
                    <input
                      type="text"
                      {...register("name")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Apellido:</label>
                    <input
                      type="text"
                      {...register("lastName")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Direccion:</label>
                    <input
                      type="text"
                      {...register("address")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">DNI:</label>
                    <input
                      type="text"
                      {...register("dni")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <button
                    className="float-right bg-primary text-base-100 hover:bg-gray-100 font-semibold py-1 px-2 border border-gray-400 rounded shadow"
                    type="submit"
                  >
                    Agregar
                  </button>
                </>
              )}

              {type === "BUS" && (
                <>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Nombre Empresa:</label>
                    <input
                      type="text"
                      {...register("businessName")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Cuit:</label>
                    <input
                      type="text"
                      {...register("cuit")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Inicio actividades:</label>
                    <input
                      type="date"
                      {...register("activityStartDate")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <h1 className="text-lg text-black font-semibold underline self-center">
                    Contacto:
                  </h1>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Nombre:</label>
                    <input
                      type="text"
                      {...register("name")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Apellido:</label>
                    <input
                      type="text"
                      {...register("lastName")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Direccion:</label>
                    <input
                      type="text"
                      {...register("address")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <article className="flex gap-3 items-center">
                    <label className="text-black">Dni:</label>
                    <input
                      type="text"
                      {...register("dni")}
                      className="border border-gray-400 rounded px-2 py-1"
                    />
                  </article>
                  <button
                    className="float-right bg-primary text-base-100 hover:bg-gray-100 font-semibold py-1 px-2 border border-gray-400 rounded shadow"
                    type="submit"
                  >
                    Agregar
                  </button>
                </>
              )}
            </section>
          </form>
        </section>
      </section>
    </>
  );
};
