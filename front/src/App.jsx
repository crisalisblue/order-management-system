import {
  Route,
  BrowserRouter as Router,
  Routes,
  Navigate,
} from "react-router-dom";
import "./App.css";

import {
  Home,
  Usuarios,
  Login,
  Clientes,
  Layout,
  UserCreate,
  UserUpdate,
  Pedidos,
  Servicios,
  Impuestos,
  Productos,
  NotFound,
} from "./pages";
import { UsersTable } from "./components/UsersTable/UsersTable";
import NotLogedinRoute from "./routes/NotLogedinRoute";
import LogedinRoute from "./routes/LogedinRoute";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/home" />} />
        <Route Component={LogedinRoute}>
          <Route path="/" Component={Layout}>
            <Route path="home" Component={Home} />
            <Route path="usuarios" Component={Usuarios}>
              <Route path="nuevo" Component={UserCreate} />
              <Route path=":id/editar" Component={UserUpdate} />
              <Route path="" Component={UsersTable} />
            </Route>
            <Route path="clientes" Component={Clientes} />
            <Route path="pedidos" Component={Pedidos} />
            <Route path="productos" Component={Productos} />
            <Route path="servicios" Component={Servicios} />
            <Route path="impuestos" Component={Impuestos} />
          </Route>
        </Route>
        <Route Component={NotLogedinRoute}>
          <Route path="/login" Component={Login} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}
