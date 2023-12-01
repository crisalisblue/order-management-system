import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleService, getSingleService } from "../../api/serviceAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const ServiciosUpdate = () => {
  const navigate = useNavigate();
  const serviceID = useParams().id;
  const { register, handleSubmit, setValue } = useForm();

  const onError = (errors, e) => console.log(errors, e);

  useEffect(() => {
    const fetchServiceData = async () => {
      try {
        const serviceData = await getSingleService(serviceID);
        console.dir(serviceData);
        console.dir(serviceID);
        Object.keys(serviceData).forEach((key) => {
          setValue(key, serviceData[key]);
        });
      } catch (error) {
        console.error("Error fetching service data:", error);
      }
    };

    fetchServiceData();
  }, [serviceID, setValue]);

  const onSubmit = async (data, e) => {
    console.log(data, e);
    data.id = serviceID;
    data.taxList = [];
    data.type = "Service";
    try {
      console.dir(await updateSingleService(data));
      showSuccessAlert();
      navigate("/servicios");
    } catch (error) {
      console.error("Error updating service:", error);
      showErrorAlert();
    }
  };

  const showSuccessAlert = () => {
    Swal.fire({
      icon: "success",
      title: "Servicio actualizado",
      showConfirmButton: false,
      timer: 1500,
    });
  };

  const showErrorAlert = () => {
    Swal.fire({
      icon: "error",
      title: "Error al actualizar servicio",
      text: "Hubo un problema al actualizar el servicio.",
    });
  };

  return (
    <form
      className="bg-[#F1F1F1] flex justify-evenly flex-wrap p-4 rounded-md drop-shadow-md w-5/6 mx-auto my-4"
      onSubmit={handleSubmit(onSubmit, onError)}
    >
      <div className="flex flex-col w-1/2">
        <label className="text-black text-xl my-5 flex justify-center items-center">
          Nombre
          <input
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            type="text"
            {...register("name")}
          />
        </label>
        <label className="text-black text-xl my-5 flex justify-center items-center">
          Precio Unitario
          <input
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            type="number"
            {...register("baseAmount")}
          />
        </label>
        <label className="text-black text-xl my-5 flex justify-center items-center">
          Garantía
          <input
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            type="text"
            {...register("warranty")}
          />
        </label>
        <label className="text-black text-xl my-5 flex justify-center items-center">
          Impuesto
          <select
            className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4"
            {...register("taxId")}
            defaultValue={0}
          >
            <option value="1">IVA</option>
            <option value="2">Otro impuesto</option>
            <option value="3">Otro impuesto más</option>
          </select>
        </label>
      </div>
      <input
        className="bg-[#001F3D] rounded-md text-white p-2 w-fit mx-auto my-2 cursor-pointer"
        type="submit"
        value="Actualizar Servicio"
      />
    </form>
  );
};
