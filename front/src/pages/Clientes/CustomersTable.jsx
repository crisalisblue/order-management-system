import { CustomerDataTable } from "../../components/DataTable/CustomerDataTable";
import { useDataFetching } from "../../api/API_Utils";
import { getAllCustomers } from "../../api/customerAPI";

export const CustomersTable = () => {
  const { data, loading, error } = useDataFetching(getAllCustomers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  return <CustomerDataTable data={data}></CustomerDataTable>;
};
