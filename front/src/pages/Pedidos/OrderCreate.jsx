import { useState, useEffect } from "react";
import { Link as RouterLink, useLocation } from "react-router-dom";
import { useDataFetching } from "../../api/API_Utils";
import { getAllCustomers, getSingleCustomer } from "../../api/customerAPI";
import { createSingleOrder, refreshOrder } from "../../api/orderAPI";
import { getAllAssets } from "../../api/assetAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const OrderCreate = () => {
  const navigate = useNavigate();

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

  const [activeTab, setActiveTab] = useState("Product");
  const [filteredAssets, setFilteredAssets] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [selectedClient, setSelectedClient] = useState("");
  const [assetQuantities, setAssetQuantities] = useState({});
  const [assetWarranties, setAssetWarranties] = useState({});
  const [customerData, setCustomerData] = useState({
    customerID: 0,
  });
  const [orderData, setOrderData] = useState({
    dateOrder: "2023-11-23",
    itemDTO: [],
    calculatedTaxDTOS: [],
    active: true,
    totalDiscount: 0,
    totalPrice: 0,
    subTotal: 0,
  });
  const [dataRefresh, setDataRefresh] = useState({
    dateOrder: "2023-11-23",
    totalDiscount: 0,
    totalPrice: 0,
    subTotal: 0,
    active: true,
    customerID: 0,
    customerName: "",
    itemDTO: [],
    calculatedTaxDTOS: [],
    action: "",
  });

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      //const response = await createSingleOrder(dataRefresh);
      if (dataRefresh.itemDTO) await createSingleOrder(dataRefresh);
      console.log(dataRefresh);
      Swal.fire({
        icon: "success",
        title: "Pedido creado",
        text: "La orden se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/pedidos");
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al crear la orden",
        text: "Hubo un problema al crear el pedido.",
      });
    }
  }

  useEffect(() => {
    if (customerData.customerID !== 0 || orderData.itemDTO.length > 0) {
      const fetchData = async () => {
        try {
          const result = await refreshOrder(orderData);
          setDataRefresh(result);
        } catch (error) {
          console.error("Error al actualizar datos:", error);
        }
      };

      fetchData();
    }
  }, [orderData, customerData]);

  useEffect(() => {
    // Filter assets based on the activeTab
    try {
      const filtered = assetsData.filter((asset) =>
        activeTab === "Product"
          ? asset.type === "Product"
          : asset.type === "Service"
      );
      setFilteredAssets(filtered);
    } catch (error) {}
  }, [activeTab, assetsData]);

  useEffect(() => {
    // Filter assets based on the searchInput
    try {
      const filtered = assetsData.filter((asset) =>
        asset.name.toLowerCase().includes(searchInput.toLowerCase())
      );
      setFilteredAssets(filtered);
    } catch (error) {}
  }, [searchInput, assetsData]);

  //Función nueva - Cambia el id de cliente dentro de la lista de la orden a enviar al back
  const handleClientChangeWithClientId = async (event) => {
    const selectedClientId = event.target.value;
    console.log("Asset data: ", assetsData);
    try {
      const customerInfo = await getSingleCustomer(selectedClientId);
      setCustomerData({
        customerID: selectedClientId,
        customerName: customerInfo.name,
      });

      setOrderData((prevOrderData) => ({
        ...prevOrderData,
        customerID: selectedClientId,
        customerName: customerInfo.name,
        action: "customer",
      }));
      setOrderData((prevOrderData) => ({
        ...prevOrderData,
        action: "calculate",
      }));
    } catch (error) {
      console.error("Error al buscar el cliente:", error);
    }
  };

  const handleTabActive = () => {
    setActiveTab(activeTab === "Product" ? "Service" : "Product");
  };

  //Agrega los items seleccionados a la orden nueva
  const handleAddAssetInOrder = async (asset) => {
    const quantity = assetQuantities[asset.id] || 1;
    const warranty = assetWarranties[asset.id] || 0;
    //Calculo el impuesto en cada item
    const taxes = asset.taxList.map((tax) => ({
      name: tax.name,
      percentage: tax.percentage,
      amount: (asset.baseAmount * quantity * tax.percentage) / 100,
    }));

    const newItem = {
      idAsset: asset.id,
      nameAsset: asset.name,
      orderDTO: {},
      itemPrice: asset.baseAmount,
      itemDitails: "Esto es una descripción",
      itemQuantity: quantity,
      discountAmount: 0,
      totalPrice: asset.baseAmount * quantity,
      warrantyYears: warranty,
      taxes: taxes,
    };
    setOrderData((prevOrderData) => ({
      ...prevOrderData,
      itemDTO: [...prevOrderData.itemDTO, newItem],
      action: "calculate",
    }));
    console.log(asset.taxList);
    // Accumulate taxes based on tax name
    setOrderData((prevOrderData) => {
      const accumulatedTaxes = prevOrderData.calculatedTaxDTOS || [];
      taxes.forEach((tax) => {
        const existingTaxIndex = accumulatedTaxes.findIndex(
          (accTax) => accTax.name === tax.name
        );

        if (existingTaxIndex !== -1) {
          accumulatedTaxes[existingTaxIndex].amount += tax.amount;
        } else {
          accumulatedTaxes.push({
            name: tax.name,
            amount: tax.amount,
          });
        }
      });

      return {
        ...prevOrderData,
        calculatedTaxDTOS: accumulatedTaxes,
      };
    });
    console.log("que hay en calculatedTax:", orderData.calculatedTaxDTOS);
    // Restablece la cantidad del activo a 0 después de agregarlo
    setAssetQuantities({
      ...assetQuantities,
      [asset.id]: 1,
    });
    setAssetWarranties({
      ...assetWarranties,
      [asset.id]: 0,
    });
  };

  //Funcion nueva, remueve items de la orden
  const handleRemoveAsset = async (assetId) => {
    const indexToRemove = orderData.itemDTO.findIndex(
      (item) => item.id === assetId
    );

    if (indexToRemove !== -1) {
      const updatedItems = [...orderData.itemDTO];
      updatedItems.splice(indexToRemove, 1);

      const accumulatedTaxes = orderData.calculatedTaxDTOS || [];
      const removedItem = orderData.itemDTO.find((item) => item.id === assetId);
      removedItem.taxes.forEach((tax) => {
        const existingTaxIndex = accumulatedTaxes.findIndex(
          (accTax) => accTax.name === tax.name
        );
        if (existingTaxIndex !== -1) {
          accumulatedTaxes[existingTaxIndex].amount -= tax.amount;
        }
      });

      setOrderData((prevOrderData) => ({
        ...prevOrderData,

        itemDTO: updatedItems,
        calculatedTaxDTOS: accumulatedTaxes,
        total: orderData.total - orderData.itemDTO[indexToRemove].subtotal,
        action: "calculate",
      }));
    }

    console.log("Refresh data en eliminar item: ", dataRefresh);
  };

  //Cambiar cantidad de cantidad
  const handleQuantityChange = (assetId, quantity) => {
    setAssetQuantities((prevQuantities) => ({
      ...prevQuantities,
      [assetId]: quantity,
    }));
  };

  //Cambiar cantidad de cantidad
  const handleWarrantyChange = (assetId, years) => {
    setAssetWarranties((prevYears) => ({
      ...prevYears,
      [assetId]: years,
    }));
  };

  const handleCancelOrder = () => {
    // Navega a la página anterior
    navigate(-1);
  };

  if (assetsError || customersError) {
    return <div>Error: {assetsError.message}</div>;
  }

  if (assetsLoading || customersLoading) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <section className="w-6/7 "></section>
      <section className={"w-6/7 p-2 rounded-md"}>
        <header className="flex justify-between p-2 text-black rounded-t-md border-b-black bg-[#F1F1F1] ">
          <h6>{orderData.id ? "00000" + orderData.id : "00000"}</h6>
          <h5>
            Cliente: {orderData.customerName ? orderData.customerName : ""}
          </h5>
          <h6>
            {orderData.dateOrder ? orderData.dateOrder : "buscando fecha"}
          </h6>
        </header>
        <form
          className="flex flex-col py-1  min-h-screen text-black rounded-b-md bg-[#FfFfFf]"
          onSubmit={handleSubmit}
        >
          <section className="flex rounded-b-md">
            <section className="w-1/3 rounded-b-md flex flex-col justify-center mx-5">
              <section className="flex rounded-b-md justify-between items-center my-1">
                <article>
                  <select
                    name="select-cliente"
                    value={selectedClient} // Establece el value como el ID del cliente seleccionado
                    onChange={(e) => handleClientChangeWithClientId(e)}
                    className="border-none p-1 px-2 relative mt-2 mx-2 rounded-md"
                  >
                    <option value="" disabled hidden>
                      Elegir cliente
                    </option>
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
                      location.pathname === "/clientes/nuevo"
                        ? "tab-active"
                        : ""
                    }  w-1/2`}
                    to="/clientes/nuevo"
                  >
                    <button className="btn-neutral text-base p-1 mx-1 relative mt-2 rounded-md bg-[#004C7D] text-white">
                      Crear Cliente
                    </button>
                  </RouterLink>
                </article>
              </section>
              <section className="flex gap-5   justify-center items-center">
                <article className="w-1/2">
                  <div className="relative mt-2 rounded-md shadow-sm">
                    <input
                      type="search"
                      className="block w-full rounded-md border-0 py-1.5 pl-3 pr-10 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                      placeholder="Buscar..."
                      value={searchInput}
                      onChange={(e) => setSearchInput(e.target.value)}
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
                <article className="w-1/2">
                  <button
                    type="button"
                    className={`btn-neutral p-1 px-1 text-sm relative mt-2 rounded-l-md ${
                      activeTab === "Product" ? "bg-[#004C7D]" : ""
                    } text-white hover:bg-[#00305F]`}
                    onClick={handleTabActive}
                  >
                    Productos
                  </button>
                  <button
                    type="button"
                    className={`btn-neutral p-1 px-1 text-sm relative mt-2 rounded-r-md ${
                      activeTab === "Service" ? "bg-[#004C7D]" : ""
                    } text-white hover:bg-[#00305F]`}
                    onClick={handleTabActive}
                  >
                    Servicios
                  </button>
                </article>
              </section>
              <section className="">
                <div className="max-h-40 overflow-y-scroll scrollbar">
                  <table className="table mt-3">
                    <thead className="bg-[#E6EFF3]">
                      <th>Nombre</th>
                      <th>P/Unit.</th>
                      <th>Cant</th>
                      <th>Garantia</th>
                      <th>Soporte</th>
                      <th> </th>
                    </thead>

                    <tbody>
                      {filteredAssets.map((asset, index) => (
                        <tr className="border-none" key={index}>
                          <td>{asset.name}</td>
                          <td>{asset.baseAmount}</td>
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
                            <input
                              className="w-10"
                              type="number"
                              min={0}
                              value={assetWarranties[asset.id] || 0}
                              onChange={(e) =>
                                handleWarrantyChange(asset.id, e.target.value)
                              }
                            />
                          </td>
                          <td>0</td>

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
            <section className="w-2/3 flex flex-col justify-between">
              <section className="max-h-60 overflow-y-scroll scrollbar">
                <table className="table">
                  <thead className="bg-[#E6EFF3]">
                    <th>Producto</th>
                    <th>P/Unit.</th>
                    <th>Cant</th>
                    <th>Sub s/imp</th>
                    <th>Sub c/imp</th>
                    <th>Garantia</th>
                    <th>Soporte</th>
                    <th></th>
                  </thead>
                  <tbody>
                    {orderData.itemDTO?.map((selectedAsset, index) => (
                      <tr className="border-none" key={index}>
                        <td>{selectedAsset.nameAsset}</td>
                        <td>{selectedAsset.itemPrice}</td>
                        <td>{selectedAsset.itemQuantity || 0}</td>
                        <td>{selectedAsset.totalPrice} </td>
                        <td>{selectedAsset.totalPrice} </td>
                        <td>{selectedAsset.warrantyYears} </td>
                        <td>0</td>
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
              <div className="flex flex-col border-2">
                <div className="flex justify-between border-y-2">
                  <div className="flex p-2 justify-center">
                    <span>Impuestos:</span>
                  </div>

                  <div className="flex flex-col p-3 border items-end">
                    {/* <span>$0</span> */}
                    {orderData.calculatedTaxDTOS.map((selectedTax, index) => (
                      <div>
                        {selectedTax.name}: ${selectedTax.amount} <br />
                      </div>
                    ))}
                  </div>
                </div>
                <div className="flex justify-between p-2">
                  <span>Total:</span>
                  <span>
                    {dataRefresh.totalPrice
                      ? "$" + dataRefresh.totalPrice
                      : "$" + 0}
                  </span>
                </div>
              </div>
            </section>
          </section>
          <section className="flex justify-evenly my-5">
            <button
              type="submit"
              className="btn-neutral p-1 px-2 relative mt-2 rounded-md bg-[#004C7D] text-white"
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
      </section>
    </>
  );
};
