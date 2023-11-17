import { DataTable } from "../DataTable/DataTable";
import { getAllTaxes, deleteSingleTax } from "../../api/TaxAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./TaxesTable.css";

export const TaxesTable = () => {
  const { data: impuestosData, loading, error } = useDataFetching(getAllTaxes);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  // Verificación de datos vacíos
  if (!impuestosData || impuestosData.length === 0) {
    return <div>No hay datos disponibles.</div>;
  }
  return (
    <DataTable
      data={impuestosData}
      keysToShow={Object.keys(...impuestosData)}
      itemName="impuestos"
      editPath="/impuestos"
      deleteFunction={deleteSingleTax}
    ></DataTable>
  );
};
