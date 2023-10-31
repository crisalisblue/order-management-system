import { Outlet } from "react-router";
import "./Layout.css";
import { NavBar } from "../../components/Navbar/Navbar";
import { useLocation } from "react-router-dom";

export const Layout = () => {
  //Con estas tres lineas le damos un id particular al main en cada vista segun corresponda
  const location = useLocation();
  const pathnameSegments = location.pathname.split("/");
  const mainId = pathnameSegments[1];
  return (
    <>
      <NavBar />
      <main id={mainId} className="bg-neutral overflow-hidden">
        <Outlet />
      </main>
      <footer className={"text-center"}>
        Finnegans Equipo Blue {new Date().getFullYear()}
      </footer>
    </>
  );
};
