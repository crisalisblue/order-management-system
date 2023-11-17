import { DataTable } from "../DataTable/DataTable";
import { getAllServices, deleteSingleService } from "../../api/ServiceAPI";
import { useDataFetching } from "../../api/API_Utils";

export const ServicesTable = () => {
  const {
    data: servicesData,
    loading,
    error,
  } = useDataFetching(getAllServices);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  // Verificar si no hay datos
  if (!servicesData || servicesData.length === 0) {
    return <div>No hay datos para mostrar.</div>;
  }
  return (
    <DataTable
      data={servicesData}
      keysToShow={Object.keys(...servicesData)}
      itemName="producto"
      editPath="/productos"
      deleteFunction={deleteSingleService}
      columnsToExclude={["taxList"]}
    />
  );
};
