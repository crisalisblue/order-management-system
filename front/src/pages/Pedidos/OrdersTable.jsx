import { OrderDataTable } from "../../components/DataTable/OrderDataTable";
import { useDataFetching } from "../../api/API_Utils";
import { getAllOrders } from "../../api/orderAPI";

export const OrdersTable = () => {
  const { data, loading, error } = useDataFetching(getAllOrders);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  return <OrderDataTable data={data}></OrderDataTable>;
};
