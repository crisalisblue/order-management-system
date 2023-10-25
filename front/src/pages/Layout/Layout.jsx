import { Outlet } from "react-router";
import "./Layout.css";
import { NavBar } from "../../components/NavBar/NavBar.jsx";

export const Layout = () => {
  return (
    <>
      <NavBar />
      <main>
        <Outlet />
      </main>
      <footer className={"text-center"}>
        Finnegans Equipo Blue {new Date().getFullYear()}
      </footer>
    </>
  );
};
