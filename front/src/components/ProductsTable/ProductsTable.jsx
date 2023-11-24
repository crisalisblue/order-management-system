import { DataTable } from "../DataTable/DataTable.jsx";
import { getAllProducts, deleteSingleProduct } from "../../api/ProductAPI.js";
import { useDataFetching } from "../../api/API_Utils.js";

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
  if (!productsData || productsData.length == 0) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={productsData}
      keysToShow={Object.keys(...productsData)}
      itemName="producto"
      editPath="/productos"
      deleteFunction={deleteSingleProduct}
      hiddenColumns={["id", "supportFee", "taxList"]}
    />
  );
};
