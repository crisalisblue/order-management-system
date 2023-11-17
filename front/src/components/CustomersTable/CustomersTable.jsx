import { DataTable } from "../DataTable/DataTable";
import { getAllCustomers, deleteSingleCustomer } from "../../api/customerAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./CustomersTable.css";

export const CustomersTable = () => {
  const {
    data: clientsData,
    loading,
    error,
  } = useDataFetching(getAllCustomers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!clientsData || clientsData.length === 0) {
    return <div>No hay datos disponibles.</div>;
  }
  return (
    <DataTable
      data={clientsData}
      keysToShow={Object.keys(...clientsData)}
      itemName="clientes"
      editPath="/clientes"
      deleteFunction={deleteSingleCustomer}
    ></DataTable>
  );
};
