import { Outlet } from "react-router";
import "./Layout.css";
import { NavBar } from "../../components/NavBar/NavBar.jsx";

export const Layout = () => {
  return (
    <>
      <nav>
        <NavBar />
      </nav>
      <main>
        <Outlet />
      </main>
      <footer>Finnegans Equipo Blue {new Date().getFullYear()}</footer>
    </>
  );
};
