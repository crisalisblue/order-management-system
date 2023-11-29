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
        setSelectedTaxes((prevTaxes) => [...prevTaxes, selectedTax]);
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
      setTaxes((prevTaxes) => [...prevTaxes, { ...removedTax }]);
    }

    setValue("tax", "");
  };

  return (
    <form
      className={
        "bg-[#F1F1F1] flex justify-evenly flex-wrap p-4 rounded-md drop-shadow-md w-5/6 mx-auto"
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
            {...register("baseAmount")}
          />
        </label>
        <input type="hidden" defaultValue={"Product"} {...register("type")} />
        {taxes.length > 0 ? (
          <label
            className={
              "text-black text-xl my-5 flex justify-center items-center"
            }
          >
            Impuesto:
            <select
              className="bg-white rounded-md drop-shadow-md text-black w-1/3"
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
        ) : (
          <p>No hay impuestos disponibles</p>
        )}
      </div>
      <div className={"w-1/2"}>
        <SelectedTaxesTable
          selectedTaxes={selectedTaxes}
          onRemoveTax={removeTax}
        />
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
