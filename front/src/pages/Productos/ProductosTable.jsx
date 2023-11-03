import { getAllProducts } from "../../api/productAPI";
import { useDataFetching } from "../../api/API_Utils";
import { ProductosTableComponent } from "../../components/ProductosTable/ProductosTableComponent";

export const ProductosTable = () => {
    const { data, loading, error } = useDataFetching(getAllProducts());

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    if (loading) {
        return <div>Loading...</div>;
    }

    return <ProductosTableComponent data={data}></ProductosTableComponent>;
};
