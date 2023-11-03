import { getAllProducts } from "../../api/productAPI";
import { useDataFetching } from "../../api/API_Utils";
import { ProductosTableComponent } from "../../components/ProductosTable/ProductosTableComponent";
import {HomeTable} from "../../components/HomeTable/HomeTable.jsx";

export const ProductosTable = () => {
    // const { data, loading, error } = useDataFetching(getAllProducts());
    //
    // if (error) {
    //     return <div>Error: {error.message}</div>;
    // }
    //
    // if (loading) {
    //     return <div>Loading...</div>;
    // }
    //
    // return <ProductosTableComponent data={data}></ProductosTableComponent>;
    return <HomeTable col1={"ID"} col2={"Nombre"} col3={"Precio Unitario"} col4={"Acciones"} numRows={10}></HomeTable>
};
