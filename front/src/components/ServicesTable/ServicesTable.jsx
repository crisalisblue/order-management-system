import { DataTable } from "../DataTable/DataTable";
import { getAllServices, deleteSingleService } from "../../api/ServiceAPI";
import { useDataFetching } from "../../api/API_Utils";

export const Services = () => {
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

  return (
    <DataTable
      data={servicesData}
      keysToShow={Object.keys(...servicesData)}
      itemName="producto"
      editPath="/productos"
      deleteFunction={deleteSingleService}
    />
  );
};
