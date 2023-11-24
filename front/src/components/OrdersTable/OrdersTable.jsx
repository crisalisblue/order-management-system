import { DataTable } from "../DataTable/DataTable.jsx";
import { getAllOrders, deleteSingleOrder } from "../../api/orderAPI.js";
import { useDataFetching } from "../../api/API_Utils.js";
import "./OrdersTable.css";

export const OrdersTable = () => {
  const { data: ordersData, loading, error } = useDataFetching(getAllOrders);

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  if (loading) {
    return <div>Loading...</div>;
  }
  if (!ordersData || ordersData.length == 0) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={ordersData}
      keysToShow={Object.keys(...ordersData)}
      itemName="pedido"
      editPath="/pedidos"
      deleteFunction={deleteSingleOrder}
    />
  );
};
