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
        <ToastContainer
          position="top-right" // Adjust the position
          autoClose={5000} // Automatically close after 5 seconds
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          // Override container style
          style={{
            background: "transparent",
          }}
          // Override toast style
          toastStyle={{
            background: "transparent",
            color: "#333",
          }}
        />
      </main>
      <footer className={"text-center"}>
        Finnegans Equipo Blue {new Date().getFullYear()}
      </footer>
    </>
  );
};
