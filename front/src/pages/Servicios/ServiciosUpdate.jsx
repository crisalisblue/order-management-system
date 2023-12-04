import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleService, getSingleService } from "../../api/serviceAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";
import { getAllTaxes } from "../../api/taxAPI.js";
import { SelectedTaxesTable } from "../../components/SelectedTaxesTable/SelectedTaxesTable.jsx";

export const ServiciosUpdate = () => {
  const navigate = useNavigate();
  const serviceID = useParams().id;
  const { register, handleSubmit, setValue } = useForm();
  const [taxes, setTaxes] = useState([]);
  const [selectedTaxes, setSelectedTaxes] = useState([]);
  const [initialValues, setInitialValues] = useState({
    taxList: selectedTaxes,
    id: serviceID,
  });

  const onError = (errors, e) => console.log(errors, e);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const serviceData = await getSingleService(serviceID);
        setInitialValues({
          name: serviceData.name,
          baseAmount: serviceData.baseAmount,
          taxList: serviceData.taxList,
          id: serviceData.id || serviceID,
        });
        setSelectedTaxes(serviceData.taxList);
        setValue("name", serviceData.name);
        setValue("baseAmount", serviceData.baseAmount);
        setValue("supportFee", serviceData.supportFee);
      } catch (error) {
        console.error("Error fetching service data:", error);
      }
    };

    fetchData();
  }, [serviceID, setValue]);

  useEffect(() => {
    const fetchTaxes = async () => {
      try {
        const allTaxes = await getAllTaxes();
        const serviceTaxNames = new Set(selectedTaxes.map((tax) => tax.name));

        const filteredTaxes = allTaxes.filter(
          (tax) => !serviceTaxNames.has(tax.name)
        );

        setTaxes(filteredTaxes);
      } catch (error) {
        console.error(error);
      }
    };

    fetchTaxes();
  }, [selectedTaxes]);

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
    } else {
      console.log(`El impuesto "${selectedTaxName}" ya está seleccionado.`);
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
  };

  const onSubmit = async (data, e) => {
    data.taxList = selectedTaxes;
    data.type = "Service";
    data.id = Number(data.id);

    try {
      await updateSingleService(data);
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

  if (!taxes.length && !selectedTaxes.length) {
    return <div>Cargando impuestos...</div>;
  }

  return (
    <section id="servicesUpdate" className="w-5/6 prose min-w-full">
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
            defaultValue={initialValues.name}
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
            defaultValue={initialValues.baseAmount}
            {...register("baseAmount")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>
        <div className="mb-4">
          <label
            htmlFor="supportFee"
            className="block text-sm font-semibold text-gray-600"
          >
            Monto de Soporte:
          </label>
          <input
            type="number"
            id="supportFee"
            defaultValue={initialValues.supportFee}
            {...register("supportFee")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>
        <input
          {...register("id", { value: initialValues.id })}
          hidden
          type="number"
          value={initialValues.id}
        />
        <div className="mb-4">
          Impuesto:
          {taxes.length > 0 ? (
            <select
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
          ) : (
            <p>No hay impuestos disponibles</p>
          )}
        </div>

        <SelectedTaxesTable
          selectedTaxes={selectedTaxes}
          onRemoveTax={removeTax}
        />

        <button
          type="submit"
          className="w-33 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Actualizar Servicio
        </button>
      </form>
    </section>
  );
};
