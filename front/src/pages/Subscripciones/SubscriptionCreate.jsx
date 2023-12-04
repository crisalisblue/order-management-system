import { createSingleSubscription } from "../../api/subscriptionAPI.js";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";
import { getAllCustomers } from "../../api/customerAPI";
import { getAllServices } from "../../api/serviceAPI.js";

export const SubscriptionCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, setValue } = useForm();
  const [customers, setCustomers] = useState([]);
  const [services, setServices] = useState([]);

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const customers = await getAllCustomers();
        setCustomers(customers);
      } catch (error) {
        console.error(error);
      }
    };

    fetchCustomers();
  }, []);
  useEffect(() => {
    const fetchServices = async () => {
      try {
        const services = await getAllServices();
        setServices(services);
      } catch (error) {
        console.error(error);
      }
    };

    fetchServices();
  }, []);

  const onSubmit = async (data) => {
    try {
      console.dir(data);
      console.dir(await createSingleSubscription(data));
      Swal.fire({
        icon: "success",
        title: "Subscripcion creada",
        text: "La subscripcion se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/subscripciones");
        }
      });
    } catch (error) {
      console.error(error);
      Swal.fire({
        icon: "error",
        title: "Error al crear subscripcion",
        text: "Hubo un problema al crear la subscripcion.",
      });
    }
  };

  const onError = (errors, e) => console.log(errors, e);

  return (
    <section id="subscriptionCreate" className="w-5/6 prose min-w-full">
      <form
        className="w-5/6 mx-auto p-6 bg-white rounded-md shadow-md"
        onSubmit={handleSubmit(onSubmit, onError)}
      >
        {customers.length > 0 && (
          <div className="mb-4">
            <label
              htmlFor="tax"
              className="block text-sm font-semibold text-gray-600"
            >
              Cliente:
            </label>
            <select
              id="customer"
              {...register("customer")}
              className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
            >
              <option value="">Seleccionar Cliente</option>
              {customers.map((customer) => (
                <option key={customer.id} value={customer.id}>
                  {customer.name}
                </option>
              ))}
            </select>
          </div>
        )}
        {services.length > 0 && (
          <div className="mb-4">
            <label
              htmlFor="tax"
              className="block text-sm font-semibold text-gray-600"
            >
              Servicio:
            </label>
            <select
              id="asset"
              {...register("asset")}
              className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
            >
              <option value="">Seleccionar Servicio</option>
              {services.map((service) => (
                <option key={service.id} value={service.id}>
                  {service.name}
                </option>
              ))}
            </select>
          </div>
        )}

        <div className="mb-4">
          <label
            htmlFor="tax"
            className="block text-sm font-semibold text-gray-600"
          >
            Servicio:
          </label>
          <select
            id="status"
            {...register("status")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          >
            <option value="">Estado</option>
            <option value="true">Activo</option>
            <option value="false">Inactivo</option>
          </select>
        </div>

        <button
          type="submit"
          className="w-33 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Agregar Subscripcion
        </button>
      </form>
    </section>
  );
};
