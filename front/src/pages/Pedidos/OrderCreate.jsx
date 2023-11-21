import { useState, useEffect } from "react";
import { Link as RouterLink, useLocation } from "react-router-dom";
import { useDataFetching } from "../../api/API_Utils";
import { getAllCustomers, getSingleCustomer } from "../../api/customerAPI";
import { createSingleOrder } from "../../api/orderAPI";
import { getAllAssets } from "../../api/assetAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const OrderCreate = () => {
  // const { setValue, handleSubmit } = useForm();
  const navigate = useNavigate();
  // const location = useLocation();

  const {
    data: assetsData,
    error: assetsError,
    loading: assetsLoading,
  } = useDataFetching(getAllAssets);

  const {
    data: customersData,
    error: customersError,
    loading: customersLoading,
  } = useDataFetching(getAllCustomers);

  const [activeTab, setActiveTab] = useState("product");
  const [selectedClient, setSelectedClient] = useState("");
  const [selectedAssets, setSelectedAssets] = useState([]);
  const [assetQuantities, setAssetQuantities] = useState({});

  async function handleSubmit(e) {
    e.preventDefault();
    const orderTotal = orderData.items.reduce(
      (total, item) => total + item.subtotal,
      0
    );

    const orderObject = {
      dateOrder: "2023-11-08",
      totalDiscount: 1000,
      totalPrice: orderTotal === 0 ? 0 : orderTotal - 1000,
      subTotal: orderTotal,
      active: true,
      customerID: orderData.customerId,
      customerName: orderData.customerName,
      itemDto: [0],
    };
    try {
      const response = await createSingleOrder(orderObject);

      if (response == "200") {
        Swal.fire({
          icon: "success",
          title: "Pedido creado",
          text: "La orden se creó exitosamente.",
        }).then((result) => {
          if (result.isConfirmed) {
            navigate("/pedidos");
          }
        });
      } else {
        console.log("status:", response);
        // Captura un status distinto de 200 y lanza un error
        throw new Error("Error al crear la orden. Status: " + response.status);
      }
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al crear la orden",
        text: "Hubo un problema al crear el pedido.",
      });
    }
  }
  const [orderData, setOrderData] = useState({
    dateOrder: "",
    totalDiscount: "",
    customerId: "",
    customerName: "",
    orderId: "",
    items: [],
    taxes: [{ idTax: "1", name: "iva", monto: 2100 }],
    total: 0,
    active: true,
    subTotal: 0,
    totalPrice: 0,
    action: "",
  });

  //Funcion nueva - Cambia el id de cliente dentro de la lista de la orden a enviar al back
  const handleClientChangeWithClientId = async (event) => {
    const selectedClientId = event.target.value;

    try {
      const data = await getSingleCustomer(selectedClientId);
      setOrderData({
        ...orderData,
        customerId: selectedClientId,
        customerName: data.name,
        action: "changeClient",
      });
    } catch (error) {
      console.error("Error al buscar el cliente:", error);
    }
  };

  const handleTabActive = () => {
    setActiveTab(activeTab === "product" ? "service" : "product");
  };

  //Agrega los items seleccionados a la orden nueva
  const handleAddAssetInOrder = (asset) => {
    const quantity = assetQuantities[asset.id] || 1;
    const newItem = {
      name: asset.name,
      price: asset.baseAmount,
      quantity: quantity < 1 ? 1 : quantity,
      subtotal: asset.baseAmount * quantity,
      itemDetails: "",
      warrantyYears: 1,
      tax: (asset.baseAmount * 21) / 100,
    };
    console.log("id item:", asset.id);

    setOrderData({
      ...orderData,
      items: [...orderData.items, newItem],
      action: "calculateItems",
      total: orderData.total + newItem.subtotal,
    });

    // Restablece la cantidad del activo a 0 después de agregarlo
    setAssetQuantities({
      ...assetQuantities,
      [asset.id]: 1,
    });
  };

  //Funcion nueva, remueve items de la orden
  const handleRemoveAsset = (assetId) => {
    const indexToRemove = orderData.items.findIndex(
      (item) => item.id === assetId
    );

    if (indexToRemove !== -1) {
      const updatedItems = [...orderData.items];
      updatedItems.splice(indexToRemove, 1);

      setOrderData({
        ...orderData,
        items: updatedItems,
        total: orderData.total - orderData.items[indexToRemove].subtotal,
      });
    }
  };

  //Cambiar cantidad de cantidad
  const handleQuantityChange = (assetId, quantity) => {
    setAssetQuantities((prevQuantities) => ({
      ...prevQuantities,
      [assetId]: quantity,
    }));
  };

  const handleCancelOrder = () => {
    // Navega a la página anterior
    navigate(-1);
  };

  if (assetsError || customersError) {
    return <div>Error: {customersError.message}</div>;
  }

  if (assetsLoading || customersLoading) {
    return <div>Loading...</div>;
  }

  return (
    <main className={"w-6/7 p-2 rounded-md"}>
      <header className="flex justify-between p-2 text-black rounded-t-md border-b-black bg-[#F1F1F1] ">
        <h6>{orderData.id ? "00000" + orderData.id : "00000"}</h6>
        <h6>6/11/2023</h6>
      </header>
      <form
        className="flex flex-col py-3 min-h-screen text-black rounded-b-md bg-[#FfFfFf]"
        onSubmit={handleSubmit}
      >
        <section className="flex rounded-b-md">
          <section className="w-1/2 rounded-b-md flex flex-col justify-center mx-5">
            <section className="flex rounded-b-md  justify-center items-center my-3">
              <article>
                <label htmlFor="">Cliente:</label>
                <select
                  name="select-cliente"
                  value={selectedClient} // Establece el value como el ID del cliente seleccionado
                  onChange={(e) => handleClientChangeWithClientId(e)}
                  className="border-none p-1 px-2 relative mt-2 mx-2 rounded-md"
                >
                  {customersData.map((client, index) => (
                    <option value={client.id} key={index}>
                      {client.name}
                    </option>
                  ))}
                </select>
              </article>
              <article>
                <RouterLink
                  className={` ${
                    location.pathname === "/pedidos/nuevo/nuevo-cliente"
                      ? "tab-active"
                      : ""
                  }  w-1/2`}
                  to="/pedidos/nuevo/nuevo-cliente"
                >
                  <button className="btn-neutral p-1 px-2 relative mt-2 rounded-md bg-[#004C7D] text-white">
                    Crear Cliente
                  </button>
                </RouterLink>
              </article>
            </section>
            <section className="flex gap-5 justify-center items-center">
              <article>
                <div className="relative mt-2 rounded-md shadow-sm">
                  <input
                    type="search"
                    className="block w-full rounded-md border-0 py-1.5 pl-5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                    placeholder="Buscar..."
                  />
                  <div className="absolute inset-y-0 right-0 flex items-center pr-3">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      className="h-5 w-5"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                    >
                      <path
                        fillRule="evenodd"
                        d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                        clipRule="evenodd"
                      />
                    </svg>
                  </div>
                </div>
              </article>
              <article>
                <button
                  type="button"
                  className={`btn-neutral p-1 px-2 relative mt-2 rounded-l-md ${
                    activeTab === "product" ? "bg-[#004C7D]" : ""
                  } text-white hover:bg-[#00305F]`}
                  onClick={handleTabActive}
                >
                  Productos
                </button>
                <button
                  type="button"
                  className={`btn-neutral p-1 px-2 relative mt-2 rounded-r-md ${
                    activeTab === "service" ? "bg-[#004C7D]" : ""
                  } text-white hover:bg-[#00305F]`}
                  onClick={handleTabActive}
                >
                  Servicios
                </button>
              </article>
            </section>
            <section className="">
              <div className="max-h-70 overflow-y-auto scrollbar">
                <table className="table mt-3">
                  <thead className="bg-[#E6EFF3]">
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>P/Unitario</th>
                    <th>Imp</th>
                    <th>Cant</th>
                    <th> </th>
                  </thead>
                  <tbody>
                    {assetsData.map((asset, index) => (
                      <tr className="border-none" key={index}>
                        <td>{asset.id}</td>
                        <td>{asset.name}</td>
                        <td>{asset.baseAmount}</td>
                        <td>{asset.taxList.map((tax) => tax)}</td>
                        <td>
                          <input
                            className="w-10"
                            type="number"
                            min={1}
                            value={assetQuantities[asset.id] || 1}
                            onChange={(e) =>
                              handleQuantityChange(asset.id, e.target.value)
                            }
                          />
                        </td>
                        <td>
                          <button
                            type="button"
                            className={"btn-xs bg-[#F1F1F1]"}
                            onClick={() => handleAddAssetInOrder(asset)}
                          >
                            +
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>
          </section>
          <section className="w-1/2 flex flex-col justify-between">
            <section className="max-h-60 overflow-y-scroll scrollbar m-2">
              Cliente:{" "}
              {orderData.customerName
                ? orderData.customerName
                : "Buscando nombre"}
              <table className="table">
                <thead className="bg-[#E6EFF3]">
                  <th>Item</th>
                  <th>P/Unit</th>
                  <th>Cant</th>
                  <th>Sub s/imp</th>
                  <th>Sub c/imp</th>
                  <th>Imp</th>
                  {/* <th>garantia</th> */}
                  <th></th>
                </thead>
                <tbody>
                  {orderData.items.map((selectedAsset, index) => (
                    <tr className="border-none" key={index}>
                      <td>{selectedAsset.name}</td>
                      <td>{selectedAsset.price}</td>
                      <td>{selectedAsset.quantity || 0}</td>
                      <td>{selectedAsset.subtotal} </td>
                      <td>{selectedAsset.subtotal + selectedAsset.tax} </td>
                      <td>{selectedAsset.tax}</td>
                      {/* <td>{selectedAsset.warrantyYears} </td> */}
                      <td>
                        <button
                          type="button"
                          className={"btn-xs bg-[#F1F1F1]"}
                          onClick={() => handleRemoveAsset(selectedAsset.id)}
                        >
                          {" "}
                          -{" "}
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </section>
            <div className="divider"></div>
            <div className="flex flex-col">
              <div className="flex justify-evenly p-2">
                <span>Impuestos</span>
                <span>$0</span>
              </div>
              <div className="flex justify-evenly  p-2">
                <span>Total</span>
                <span>{orderData.total}</span>
              </div>
            </div>
          </section>
        </section>
        <section className="flex justify-evenly my-5">
          <button
            type="submit"
            className="btn-neutral p-1 px-2 relative mt-2 rounded-md bg-[#004C7D] text-white"
            //onClick={onCreateOrder}
          >
            Crear pedido
          </button>
          <button
            type="button"
            className="btn-neutral p-1 px-2 relative mt-2 rounded-md bg-[#004C7D] text-white"
            onClick={handleCancelOrder}
          >
            Cancelar pedido
          </button>
        </section>
      </form>
    </main>
  );
};
