import { Outlet } from "react-router";
import "./Layout.css";
import { Navbar } from "../../components/Navbar/Navbar.jsx";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export const Layout = () => {
  return (
    <>
      <nav>
        <Navbar />
      </nav>
      <main>
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
      <footer>footer</footer>
    </>
  );
};
