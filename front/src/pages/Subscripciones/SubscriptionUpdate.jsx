import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import {
  updateSingleSubscription,
  getSingleSubscription,
} from "../../api/subscriptionAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";
import { getAllCustomers } from "../../api/customerAPI";
import { getAllServices } from "../../api/serviceAPI.js";

export const SubscriptionUpdate = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { register, handleSubmit, setValue } = useForm();
  const [customers, setCustomers] = useState([]);
  const [services, setServices] = useState([]);
  const [subscription, setSubscription] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const subscriptionData = await getSingleSubscription(id);
        setSubscription(subscriptionData);

        // Setear los valores iniciales para los campos del formulario
        setValue("customer", subscriptionData.customer.id);
        setValue("asset", subscriptionData.asset.id);
        setValue("status", subscriptionData.status.toString());
      } catch (error) {
        console.error("Error fetching subscription data:", error);
      }
    };

    fetchData();
  }, [id, setValue]);

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
      data.id = id;
      console.dir(await updateSingleSubscription(data));
      Swal.fire({
        icon: "success",
        title: "Subscripcion actualizada",
        text: "La subscripcion se actualizó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/subscripciones");
        }
      });
    } catch (error) {
      console.error(error);
      Swal.fire({
        icon: "error",
        title: "Error al actualizar subscripcion",
        text: "Hubo un problema al actualizar la subscripcion.",
      });
    }
  };

  const onError = (errors, e) => console.log(errors, e);

  if (!subscription) {
    return <div>Cargando...</div>;
  }

  return (
    <section id="subscriptionUpdate" className="w-5/6 prose min-w-full">
      <form
        className="w-5/6 mx-auto p-6 bg-white rounded-md shadow-md"
        onSubmit={handleSubmit(onSubmit, onError)}
      >
        {customers.length > 0 && (
          <div className="mb-4">
            <label
              htmlFor="customer"
              className="block text-sm font-semibold text-gray-600"
            >
              Cliente:
            </label>
            <select
              id="customer"
              {...register("customer")}
              className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
            >
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
              htmlFor="asset"
              className="block text-sm font-semibold text-gray-600"
            >
              Servicio:
            </label>
            <select
              id="asset"
              {...register("asset")}
              className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
            >
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
            htmlFor="status"
            className="block text-sm font-semibold text-gray-600"
          >
            Estado:
          </label>
          <select
            id="status"
            {...register("status")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          >
            <option value="true">Activo</option>
            <option value="false">Inactivo</option>
          </select>
        </div>

        <button
          type="submit"
          className="w-1/3 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Actualizar Subscripcion
        </button>
      </form>
    </section>
  );
};
