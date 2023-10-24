import { Outlet } from "react-router";
import "./Layout.css";
import { NavBar } from "../../components/NavBar/NavBar.jsx";

export const Layout = () => {
  return (
    <>
        <header className={"flex items-center justify-center"}>
            <h1 className={"text-center"}>Finnegans</h1>
        </header>
        <nav>
            <NavBar />
        </nav>
        <main>
            <Outlet />
        </main>
        <footer className={"text-center"}>Finnegans Equipo Blue {new Date().getFullYear()}</footer>
    </>
  );
};
