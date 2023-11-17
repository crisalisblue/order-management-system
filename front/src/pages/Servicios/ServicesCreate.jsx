import { createSingleService } from "../../api/serviceAPI.js";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

import { TaxesTable } from "../../components/TaxTable/TaxesTable.jsx";

export const ServicesCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    try {
      console.dir(data);
      console.dir(await createSingleService(data));
      Swal.fire({
        icon: "success",
        title: "Producto creado",
        text: "El producto se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/productos");
        }
      });
    } catch (error) {
      console.error(error);
      Swal.fire({
        icon: "error",
        title: "Error al crear producto",
        text: "Hubo un problema al crear el producto.",
      });
    }
  };

  const onError = (errors, e) => console.log(errors, e);

  return (
    <form
      className={
        "bg-[#F1F1F1] flex justify-evenly flex-wrap p-4 rounded-md drop-shadow-md w-5/6 mx-auto my-4"
      }
      onSubmit={handleSubmit(onSubmit, onError)}
    >
      <div className={"flex flex-col w-1/2"}>
        <label
          className={"text-black text-xl my-5 flex justify-center items-center"}
        >
          Nombre
          <input
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            type="text"
            {...register("name")}
          />
        </label>
        <label
          className={"text-black text-xl my-5 flex justify-center items-center"}
        >
          Precio Unitario
          <input
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            type="number"
            {...register("mountBase")}
          />
        </label>

        <label
          className={"text-black text-xl my-5 flex justify-center items-center"}
        >
          Impuesto
          <select className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4">
            <option value="1">IVA</option>
            <option value="2">IVA</option>
            <option value="3">IVA</option>
          </select>
        </label>
      </div>
      <div className={"w-1/2"}>
        <TaxesTable />
      </div>
      <input
        className={
          "bg-[#001F3D] rounded-md text-white p-2 w-fit mx-auto my-2 cursor-pointer"
        }
        type="submit"
        value="Agregar Producto"
      />
    </form>
  );
};
