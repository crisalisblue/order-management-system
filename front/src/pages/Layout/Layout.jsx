import { Outlet } from "react-router";
import "./Layout.css";
import {Navbar} from "../../components/Navbar/Navbar.jsx";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export const Layout = () => {
    const Copyright = (props) => {
        return (
            <Box className="copyright">
                <Typography
                    variant="body2"
                    color="text.secondary"
                    align="center"
                    {...props}
                >
                    {"Copyright © "}
                    Finnegans Equipo Blue {new Date().getFullYear()}
                </Typography>
            </Box>
        );
    };
  return (
    <>
      <nav>
          <Navbar/>
      </nav>
      <main>
        <Outlet />
      </main>
      <footer>
          <Copyright/>
      </footer>
    </>
  );
};
