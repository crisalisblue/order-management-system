import { createSingleCustomer } from "../../api/customerAPI.js";
import { useState } from "react";
import { Link as RouterLink, useLocation } from "react-router-dom";

import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
export const OrderClientCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, reset, control, setValue } = useForm();
  const onSubmit = async (data, e) => {
    console.dir(data);
    console.dir(await createSingleCustomer(data));
    navigate("/pedidos/nuevo");
  };
  const onError = (errors, e) => console.log(errors, e);
  const [type, setType] = useState("");

  {
    if (type == "Persona") {
      return (
        <>
          <section className={" w-5/6 p-5 m-auto bg-[#F1F1F1]"}>
            <section
              className={"flex justify-center items-center gap-3  flex-col"}
            >
              <form
                className={
                  "flex w-42 justify-evenly items-center gap-3 my-20  flex-col text-black"
                }
                onSubmit={handleSubmit(onSubmit, onError)}
              >
                <section
                  className={
                    "flex w-44 justify-evenly items-end gap-3 flex-col"
                  }
                >
                  <article className={"flex gap-3"}>
                    <label className={"text-black"}>Tipo:</label>
                    <input
                      type="text"
                      defaultValue={"Persona"}
                      readOnly
                      {...register("type")}
                    />
                  </article>
                  <article className={"flex gap-3"}>
                    <label className={"text-black"}>Nombre:</label>
                    <input type="text" {...register("name")} />
                  </article>
                  <article className={"flex gap-3"}>
                    <label className={"text-black"}>Apellido:</label>
                    <input type="text" {...register("lastName")} />
                  </article>
                  <article className={"flex gap-3"}>
                    <label className={"text-black"}>DNI:</label>
                    <input type="text" {...register("dni")} />
                  </article>
                </section>
                <button
                  className={"btn my-2 text-white bg-[#001F3D]"}
                  type="submit"
                >
                  Agregar
                </button>
              </form>
            </section>
          </section>
        </>
      );
    } else if (type == "Empresa") {
      return (
        <>
          <section
            className={" w-5/6 p-5 flex justify-center m-auto bg-[#F1F1F1]"}
          >
            <section className={"flex justify-center  gap-3  flex-col"}>
              <form
                className={
                  "flex justify-center text-black items-end gap-3 my-20  flex-col"
                }
                onSubmit={handleSubmit(onSubmit, onError)}
              >
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Tipo:</label>
                  <input
                    type="text"
                    defaultValue={"Empresa"}
                    readOnly
                    {...register("type")}
                  />
                </article>
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Nombre Empresa:</label>
                  <input type="text" {...register("businessName")} />
                </article>
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Cuit:</label>
                  <input type="text" {...register("cuit")} />
                </article>
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Inicio actividades:</label>
                  <input type="text" {...register("activityStartDate")} />
                </article>
                <h1 className="text-lg text-black font-semibold underline self-center">
                  Contacto:
                </h1>

                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Nombre:</label>
                  <input type="text" {...register("name")} />
                </article>
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Apellido:</label>
                  <input type="text" {...register("lastName")} />
                </article>
                <article className={"flex gap-3"}>
                  <label className={"text-black"}>Dni:</label>
                  <input type="text" {...register("dni")} />
                </article>
                <button className={"btn text-white bg-[#001F3D]"} type="submit">
                  Agregar
                </button>
              </form>
            </section>
          </section>
        </>
      );
    } else {
      return (
        <>
          <section
            className={"flex gap-3 justify-evenly my-20 items-center flex-col"}
          >
            <button
              className={"btn text-white bg-[#001F3D] btn-lg"}
              onClick={() => setType("Empresa")}
            >
              Empresa
            </button>
            <button
              className={"btn text-white bg-[#001F3D] btn-lg"}
              onClick={() => setType("Persona")}
            >
              Persona
            </button>
          </section>
        </>
      );
    }
  }
};
