import "./Home.css";
import { HomeTable } from "../../components/HomeTable/HomeTable";

export const Home = () => {
  return (
    <>
      <h1 style={{ margin: "0px", textAlign: "center" }}>Home</h1>
      <div className={"tables-container"}>
        <HomeTable />
        <HomeTable />
      </div>
    </>
  );
};
