import { DataTable } from "../DataTable/DataTable";
import {
  getAllCustomers,
  deleteSingleCustomer,
} from "../../api/customerAPI.js";
import { useDataFetching } from "../../api/API_Utils.js";

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
  if (!clientesData || clientesData.length == 0) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={clientesData}
      keysToShow={Object.keys(...clientesData)}
      itemName="clientes"
      editPath="/clientes"
      deleteFunction={deleteSingleCustomer}
      hiddenColumns={["id"]}
      columnOrder={[
        "name",
        "lastName",
        "dni",
        "cuit",
        "address",
        "businessName",
        "cuit",
        "activityStartDate",
      ]}
    />
  );
};
