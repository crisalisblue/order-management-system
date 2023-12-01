import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleProduct, getSingleProduct } from "../../api/productAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";
import { getAllTaxes } from "../../api/taxAPI.js";
import { SelectedTaxesTable } from "../../components/SelectedTaxesTable/SelectedTaxesTable.jsx";

export const ProductsUpdate = () => {
  const navigate = useNavigate();
  const productID = useParams().id;
  const { register, handleSubmit, setValue } = useForm();
  const [taxes, setTaxes] = useState([]);
  const [selectedTaxes, setSelectedTaxes] = useState([]);

  const onError = (errors, e) => console.log(errors, e);

  useEffect(() => {
    const fetchProductData = async () => {
      try {
        const productData = await getSingleProduct(productID);
        Object.keys(productData).forEach((key) => {
          setValue(key, productData[key]);
        });
      } catch (error) {
        console.error("Error fetching product data:", error);
      }
    };

    fetchProductData();
  }, [productID, setValue]);

  useEffect(() => {
    const fetchTaxes = async () => {
      try {
        const taxList = await getAllTaxes();
        setTaxes(taxList);
      } catch (error) {
        console.error(error);
      }
    };

    fetchTaxes();
  }, []);

  const handleTaxChange = (e) => {
    const selectedTaxName = e.target.value;
    const isTaxSelected = selectedTaxes.some((tax) => tax.name === selectedTaxName);

    if (!isTaxSelected) {
      const selectedTax = taxes.find((tax) => tax.name === selectedTaxName);
      if (selectedTax) {
        setSelectedTaxes([...selectedTaxes, selectedTax]);
      }

      const updatedTaxes = taxes.filter((tax) => tax.name !== selectedTaxName);
      setTaxes(updatedTaxes);

      setValue("taxId", selectedTaxName);
    } else {
      console.log(`El impuesto "${selectedTaxName}" ya está seleccionado.`);
      setValue("taxId", "");
    }
  };

  const removeTax = (taxName) => {
    const updatedSelectedTaxes = selectedTaxes.filter((tax) => tax.name !== taxName);
    setSelectedTaxes(updatedSelectedTaxes);

    const removedTax = selectedTaxes.find((tax) => tax.name === taxName);

    if (removedTax) {
      setTaxes([...taxes, removedTax]);
    }

    setValue("taxId", "");
  };

  const onSubmit = async (data, e) => {
    data.id = productID;
    data.taxList = selectedTaxes;
    data.type = "Product";
    try {
      await updateSingleProduct(data);
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
    <section id="productsUpdate" className="w-5/6 prose min-w-full">
      <form
        className="w-5/6 mx-auto p-6 bg-white rounded-md shadow-md"
        onSubmit={handleSubmit(onSubmit, onError)}
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
            htmlFor="baseAmount"
            className="block text-sm font-semibold text-gray-600"
          >
            Precio Unitario:
          </label>
          <input
            type="number"
            id="baseAmount"
            {...register("baseAmount")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="warranty"
            className="block text-sm font-semibold text-gray-600"
          >
            Garantía:
          </label>
          <input
            type="text"
            id="warranty"
            {...register("warranty")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="taxId"
            className="block text-sm font-semibold text-gray-600"
          >
            Impuesto:
            <select
              id="taxId"
              className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
              {...register("taxId")}
              defaultValue={0}
              onChange={handleTaxChange}
            >
              <option value="">Seleccionar Impuesto</option>
              {taxes.map((tax) => (
                <option key={tax.id} value={tax.name}>
                  {tax.name}
                </option>
              ))}
            </select>
          </label>
        </div>

        <SelectedTaxesTable selectedTaxes={selectedTaxes} onRemoveTax={removeTax} />

        <button
          type="submit"
          className="w-33 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Actualizar Producto
        </button>
      </form>
    </section>
  );
};
