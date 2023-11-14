import "./Home.css";
import { HomeTable } from "../../components/HomeTable/HomeTable";

export const Home = () => {
  return (
    <div className={"flex flex-wrap"}>
      <h1 className={"w-full text-black text-center text-3xl mb-4"}>Home</h1>
        <div className={"table-container flex-1"}>
            <h2 className={"text-black text-center text-xl my-4"}>Últimos Clientes</h2>
            <HomeTable col1={"ID"} col2={"Nombre"} col3={"Tipo"} col4={"Acciones"} numRows={9}/>
        </div>
        <div className={"table-container flex-1"}>
            <h2 className={"text-black text-center text-xl my-4"}>Últimos Pedidos</h2>
            <HomeTable col1={"ID Cliente"} col2={"Fecha"} col3={"Total"} col4={"Acciones"} numRows={9}/>
        </div>
    </div>
  );
};
