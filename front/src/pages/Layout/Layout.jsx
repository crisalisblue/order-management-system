import { Outlet } from "react-router";
import "./Layout.css";

export const Layout = () => {
  return (
    <>
      <nav>nav</nav>
      <aside>aside</aside>
      <main>
        <Outlet />
      </main>
      <footer>footer</footer>
    </>
  );
};
