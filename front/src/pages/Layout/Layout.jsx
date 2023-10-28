import { Outlet } from "react-router";
import "./Layout.css";
import { NavBar } from "../../components/NavBar/NavBar.jsx";
import { useLocation } from "react-router-dom";

export const Layout = () => {
  const location = useLocation();
  const mainId = location.pathname.substring(1);

  return (
    <>
      <NavBar />
      <main id={mainId}>
        <Outlet />
      </main>
      <footer className={"text-center"}>
        Finnegans Equipo Blue {new Date().getFullYear()}
      </footer>
    </>
  );
};
