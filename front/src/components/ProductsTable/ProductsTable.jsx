import { DataTable } from "../DataTable/DataTable";
import { getAllProducts, deleteSingleProduct } from "../../api/ProductAPI";
import { useDataFetching } from "../../api/API_Utils";

export const ProductsTable = () => {
  const {
    data: productsData,
    loading,
    error,
  } = useDataFetching(getAllProducts);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  
  if (productsData.length == 0) {
    return <div>No Data...</div>;
  }

  return (
    <DataTable
      data={productsData}
      keysToShow={Object.keys(...productsData)}
      itemName="producto"
      editPath="/productos"
      deleteFunction={deleteSingleProduct}
    />
  );
};
