import { Outlet } from "react-router";
import "./Layout.css";
import {SidebarDrawer} from "../../components/Navbar/SidebarDrawer.jsx";
import {Navbar} from "../../components/Navbar/Navbar.jsx";

export const Layout = () => {
  return (
    <>
      <nav>
          <Navbar/>
      </nav>
      <main>
        <Outlet />
      </main>
      <footer>footer</footer>
    </>
  );
};
