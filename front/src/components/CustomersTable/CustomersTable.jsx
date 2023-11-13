import { DataTable } from "../DataTable/DataTable";
import { getAllCustomers, deleteSingleCustomer } from "../../api/customerAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./CustomersTable.css";

export const CustomersTable = () => {
  const {
    data: clientesData,
    loading,
    error,
  } = useDataFetching(getAllCustomers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  return (
    <DataTable
      data={clientesData}
      keysToShow={Object.keys(...clientesData)}
      itemName="clientes"
      editPath="/clientes"
      deleteFunction={deleteSingleCustomer}
    ></DataTable>
  );
};
