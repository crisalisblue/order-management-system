import { TaxDataTable } from "../DataTable/TaxDataTable";
import { getAllTaxes } from "../../api/TaxAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./TaxesTable.css";

export const TaxesTable = () => {
  const { data, loading, error } = useDataFetching(getAllTaxes);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  return <TaxDataTable data={data}></TaxDataTable>;
};
