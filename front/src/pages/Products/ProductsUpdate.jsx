import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleProduct, getSingleProduct } from "../../api/productAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const ProductsUpdate = () => {
  const navigate = useNavigate();
  const productID = useParams().id;
  const { register, handleSubmit, setValue } = useForm();

  const onError = (errors, e) => console.log(errors, e);

  // Obtener datos del producto antes de renderizar el componente
  useEffect(() => {
    const fetchProductData = async () => {
      try {
        const productData = await getSingleProduct(productID);

        // Setear los valores iniciales del formulario con los datos obtenidos
        Object.keys(productData).forEach((key) => {
          setValue(key, productData[key]);
        });
      } catch (error) {
        console.error("Error fetching product data:", error);
      }
    };

    fetchProductData();
  }, [productID, setValue]);

  const onSubmit = async (data, e) => {
    console.log(data, e);
    try {
      console.dir(await updateSingleProduct(data));
      showSuccessAlert();
      navigate("/productos");
    } catch (error) {
      console.error("Error updating product:", error);
      showErrorAlert();
    }
  };

  const showSuccessAlert = () => {
    Swal.fire({
      icon: "success",
      title: "Producto actualizado",
      showConfirmButton: false,
      timer: 1500,
    });
  };

  const showErrorAlert = () => {
    Swal.fire({
      icon: "error",
      title: "Error al actualizar producto",
      text: "Hubo un problema al actualizar el producto.",
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
            {...register("mountBase")}
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
          <select className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4">
            <option value="1">IVA</option>
            <option value="2">IVA</option>
            <option value="3">IVA</option>
          </select>
        </label>
      </div>
      <input
        className="bg-[#001F3D] rounded-md text-white p-2 w-fit mx-auto my-2 cursor-pointer"
        type="submit"
        value="Actualizar Producto"
      />
    </form>
  );
};
