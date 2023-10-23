import { Link as RouterLink } from "react-router-dom";
import "./Navbar.css";

import { useAuthContext } from "../../context/UseAuthContext";

export const NavBar = () => {
  const { logout } = useAuthContext();

  return <nav>nav</nav>;
};
