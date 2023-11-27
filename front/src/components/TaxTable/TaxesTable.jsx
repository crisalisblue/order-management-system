import { DataTable } from "../DataTable/DataTable.jsx";
import { getAllTaxes, deleteSingleTax } from "../../api/TaxAPI.js";
import { useDataFetching } from "../../api/API_Utils.js"; 

export const TaxesTable = () => {
  const { data: impuestosData, loading, error } = useDataFetching(getAllTaxes);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  if (!impuestosData || impuestosData.length == 0) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={impuestosData}
      keysToShow={Object.keys(...impuestosData)}
      itemName="impuestos"
      editPath="/impuestos"
      deleteFunction={deleteSingleTax}
      hiddenColumns={["baseAmount", "id"]}
    ></DataTable>
  );
};
