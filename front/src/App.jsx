import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import { Home } from "./pages/Home/Home.jsx";
import { UserCRUD } from "./pages/Usuarios/UserCRUD.jsx";
import { Login } from "./pages";
import { Layout } from "./pages/Layout/Layout";
import { UserCreate } from "./pages/Usuarios/UserCreate";
import { UsersTable } from "./pages/Usuarios/UsersTable";
import { UserUpdate } from "./pages/Usuarios/UserUpdate";
import NotLogedinRoute from "./routes/NotLogedinRoute";
import LogedinRoute from "./routes/LogedinRoute";
// import NotLogedinRoute from "./routes/NotLogedinRoute";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route Component={LogedinRoute}>
          <Route path="/" Component={Layout}>
            <Route path="home" Component={Home} />
            <Route path="usuarios" Component={UserCRUD}>
              <Route path="nuevo" Component={UserCreate} />
              <Route path=":id/editar" Component={UserUpdate} />
              <Route path="" Component={UsersTable} />
            </Route>
          </Route>
        </Route>
        <Route Component={NotLogedinRoute}>
          <Route path="/login" Component={Login} />
        </Route>
      </Routes>
    </Router>
  );
}
