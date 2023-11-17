import { OrderDataTable } from "../../components/DataTable/OrderDataTable";
//import { useDataFetching } from "../../api/API_Utils";
//import { getAllCustomers } from "../../api/customerAPI";

export const OrdersTable = () => {
  //const { data, loading, error } = useDataFetching(getAllOrders);

  const data = [
    {
      numPedido: "0000001",
      fecha: "28/10/2023",
      Cliente: "Walmart",
      Total: "$9999.99",
      Baja: false,
    },
    {
      numPedido: "0000002",
      fecha: "28/10/2023",
      Cliente: "Coto",
      Total: "$9999.99",
      Baja: false,
    },
    {
      numPedido: "0000003",
      fecha: "28/10/2023",
      Cliente: "Disco",
      Total: "$9999.99",
      Baja: true,
    },
    {
      numPedido: "0000004",
      fecha: "28/10/2023",
      Cliente: "Carrefour",
      Total: "$9999.99",
      Baja: false,
    },
    {
      numPedido: "0000005",
      fecha: "28/10/2023",
      Cliente: "Día",
      Total: "$9999.99",
      Baja: true,
    },
  ];

  // if (error) {
  //   return <div>Error: {error.message}</div>;
  // }

  // if (loading) {
  //   return <div>Loading...</div>;
  // }

  return <OrderDataTable data={data}></OrderDataTable>;
};
