import { useState } from "react";
import { Link as RouterLink, useLocation } from "react-router-dom";
import { useDataFetching } from "../../api/API_Utils";
import { getAllCustomers } from "../../api/customerAPI";
// import { getAllProducts } from "../../api/productAPI";
// import { getAllServices } from "../../api/serviceAPI";
import { getAllAssets } from "../../api/assetAPI";

export const OrderCreate = () => {
  const {
    data: assetsData,
    error: assetsError,
    loading: assetsLoading,
  } = useDataFetching(getAllAssets);
  // const {
  //   data: productsData,
  //   error: productsError,
  //   loading: productsLoading,
  // } = useDataFetching(getAllProducts);
  // const {
  //   data: servicesData,
  //   error: servicesError,
  //   loading: servicesLoading,
  // } = useDataFetching(getAllServices);
  const {
    data: customersData,
    error: customersError,
    loading: customersLoading,
  } = useDataFetching(getAllCustomers);

  const [activeTab, setActiveTab] = useState("products");
  const [selectedClient, setSelectedClient] = useState("");
  const [selectedAssets, setSelectedAssets] = useState([]);
  const [assetQuantities, setAssetQuantities] = useState({});

  // const handleTabChange = () => {
  //   setActiveTab(activeTab === "products" ? "services" : "products");
  // };

  const handleClientChange = (event) => {
    setSelectedClient(event.target.value);
    console.log(selectedClient);
  };

  const handleAddAsset = (asset) => {
    setSelectedAssets([...selectedAssets, asset]);
  };

  const handleQuantityChange = (assetId, quantity) => {
    setAssetQuantities({ ...assetQuantities, [assetId]: quantity });
  };

  if (assetsError || customersError) {
    return <div>Error: {customersError.message}</div>;
  }

  if (assetsLoading || customersLoading) {
    return <div>Loading...</div>;
  }

  return (
    <main className="w-6/7 p-2">
      <header className="flex justify-between p-2 text-black border-b-2 border-black bg-[#F1F1F1] ">
        <h6>Numero pedido</h6>
        <h6>6/11/2023</h6>
      </header>

      <body className=" py-3 text-black bg-[#F1F1F1] ">
        <section className="flex">
          <section className="w-1/2 flex flex-col justify-center items-center">
            <section className="flex justify-between items-center my-5">
              <article>
                <label htmlFor="">Cliente:</label>
                <select
                  name="select-cliente"
                  value={selectedClient}
                  onChange={handleClientChange}
                >
                  {customersData.map((client, index) => (
                    <option value={client.id} key={index}>
                      {client.type === "Empresa"
                        ? client.businessName
                        : `${client.lastName}, ${client.name}`}
                    </option>
                  ))}
                </select>
                {selectedClient}
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
                  Agregar Cliente
                </RouterLink>
              </article>
            </section>
            <section className="flex gap-5 items-center">
              <article>
                <input className="border-2 border-black" type="text" />
                <button className="btn">b</button>
              </article>
              <article>
                <button className="btn">Productos</button>
                <button className="btn">Servicios</button>
              </article>
            </section>
            <table className="w-5/6 mt-3">
              <thead className="">
                <th>Nombre</th>
                <th>P/Unitario</th>
                <th>Cantidad</th>
                <th></th>
              </thead>
              <tbody>
                {assetsData.map((asset, index) => (
                  <tr key={index}>
                    <td>{asset.name}</td>
                    <td>{asset.mountBase}</td>
                    <td>
                      <input
                        type="number"
                        value={assetQuantities[asset.id] || 0}
                        onChange={(e) =>
                          handleQuantityChange(asset.id, e.target.value)
                        }
                      />
                    </td>
                    <td>
                      <button onClick={() => handleAddAsset(asset)}>+</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </section>
          <section className="w-1/2 flex flex-col justify-center items-center">
            <table>
              <thead>
                <th>Item</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal s/impuesto</th>
                <th>Impuestos</th>
                <th>Subtotal c/impuesto</th>
              </thead>
              <tbody>
                {selectedAssets.map((selectedAsset, index) => (
                  <tr key={index}>
                    <td>{selectedAsset.name}</td>
                    <td>{selectedAsset.mountBase}</td>
                    <td>{assetQuantities[selectedAsset.id] || 0}</td>
                    <td>
                      {selectedAsset.mountBase *
                        assetQuantities[selectedAsset.id]}{" "}
                    </td>
                    <td>{selectedAsset.mountBase}</td>
                    <td>
                      {selectedAsset.mountBase *
                        assetQuantities[selectedAsset.id] +
                        selectedAsset.mountBase}{" "}
                    </td>
                    <td>
                      <button>-</button>
                    </td>
                  </tr>
                ))}
              </tbody>
              <tfoot className="flex flex-col justify-center items-center">
                <tr className="flex justify-center items-center">
                  <th>Impuestos</th>
                  <td>$999.99</td>
                </tr>
                <tr className="flex justify-center items-center">
                  <th scope="row-2">Total</th>
                  <td>$999.99</td>
                </tr>
              </tfoot>
            </table>
          </section>
        </section>
        <section className="flex justify-evenly my-5">
          <button>Crear pedido</button>
          <button>Cancelar pedido</button>
        </section>
      </body>
    </main>
  );
};
