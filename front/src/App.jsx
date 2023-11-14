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
  CustomerCreate,
  CustomersTable,
  CustomerUpdate,
  Pedidos,
  Servicios,
  Taxes,
  TaxCreate,
  TaxUpdate,
  Productos,
  NotFound,
} from "./pages";
import { UsersTable } from "./components/UsersTable/UsersTable";
import { TaxesTable } from "./components/TaxTable/TaxesTable";
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
            <Route path="clientes" Component={Clientes}>
              <Route path="nuevo" Component={CustomerCreate} />
              <Route path=":id/editar" Component={CustomerUpdate} />
              <Route path="" Component={CustomersTable} />
            </Route>
            <Route path="pedidos" Component={Pedidos} />
            <Route path="productos" Component={Productos} />
            <Route path="servicios" Component={Servicios} />
            <Route path="impuestos" Component={Taxes}>
              <Route path="nuevo" Component={TaxCreate} />
              <Route path=":id/editar" Component={TaxUpdate} />
              <Route path="" Component={TaxesTable} />
            </Route>
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