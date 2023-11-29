import { createSingleProduct } from "../../api/productAPI.js";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";
import { getAllTaxes } from "../../api/taxAPI.js";
import { SelectedTaxesTable } from "../../components/SelectedTaxesTable/SelectedTaxesTable.jsx";

export const ProductsCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, setValue } = useForm();
  const [taxes, setTaxes] = useState([]);
  const [selectedTaxes, setSelectedTaxes] = useState([]);

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

  const onSubmit = async (data) => {
    try {
      data.taxList = selectedTaxes;
      data.type = "Product";
      console.dir(data);
      console.dir(await createSingleProduct(data));
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

  const handleTaxChange = (e) => {
    const selectedTaxName = e.target.value;

    const isTaxSelected = selectedTaxes.some(
      (tax) => tax.name === selectedTaxName
    );

    if (!isTaxSelected) {
      const selectedTax = taxes.find((tax) => tax.name === selectedTaxName);
      if (selectedTax) {
        setSelectedTaxes([...selectedTaxes, selectedTax]);
      }

      const updatedTaxes = taxes.filter((tax) => tax.name !== selectedTaxName);
      setTaxes(updatedTaxes);

      setValue("tax", selectedTaxName);
    } else {
      console.log(`El impuesto "${selectedTaxName}" ya está seleccionado.`);
      setValue("tax", "");
    }
  };

  const removeTax = (taxName) => {
    const updatedSelectedTaxes = selectedTaxes.filter(
      (tax) => tax.name !== taxName
    );
    setSelectedTaxes(updatedSelectedTaxes);

    const removedTax = selectedTaxes.find((tax) => tax.name === taxName);
    if (removedTax) {
      setTaxes([...taxes, removedTax]);
    }

    setValue("tax", "");
  };

  return (
    <section id="productsCreate" className="w-5/6 prose min-w-full">
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

        {taxes.length > 0 ? (
          <div className="mb-4">
            <label
              htmlFor="tax"
              className="block text-sm font-semibold text-gray-600"
            >
              Impuesto:
              <select
                id="tax"
                className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
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
        ) : (
          <p>No hay impuestos disponibles</p>
        )}

        <SelectedTaxesTable
          selectedTaxes={selectedTaxes}
          onRemoveTax={removeTax}
        />

        <button
          type="submit"
          className="w-33 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Agregar Producto
        </button>
      </form>
    </section>
  );
};
