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
  CustomerUpdate,
  OrderClientCreate,
  OrderCreate,
  OrderUpdate,
  Pedidos,
  Servicios,
  Taxes,
  TaxCreate,
  TaxUpdate,
  Products,
  NotFound,
  Subscripciones,
  ProductsCreate,
  ProductsUpdate,
  ServiciosCreate,
  ServiciosUpdate,
  SubscriptionCreate,
  SubscriptionUpdate,
} from "./pages";
import { UsersTable } from "./components/UsersTable/UsersTable.jsx";
import { TaxesTable } from "./components/TaxTable/TaxesTable.jsx";
import { CustomersTable } from "./components/CustomersTable/CustomersTable.jsx";
import NotLogedinRoute from "./routes/NotLogedinRoute";
import LogedinRoute from "./routes/LogedinRoute";
import { ProductsTable } from "./components/ProductsTable/ProductsTable.jsx";
import { OrdersTable } from "./components/OrdersTable/OrdersTable.jsx";
import { ServicesTable } from "./components/ServicesTable/ServicesTable.jsx";
import { SubscriptionTable } from "./components/SubscriptionTable/SubscriptionTable.jsx";

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
            <Route path="productos" Component={Products}>
              <Route path="nuevo" Component={ProductsCreate} />
              <Route path=":id/editar" Component={ProductsUpdate} />
              <Route path="" Component={ProductsTable} />
            </Route>
            <Route path="pedidos" Component={Pedidos}>
              <Route path="nuevo" Component={OrderCreate} />
              <Route path="nuevo/nuevo-cliente" Component={OrderClientCreate} />
              <Route path=":id/editar" Component={OrderUpdate} />
              <Route path="" Component={OrdersTable} />
            </Route>
            <Route path="servicios" Component={Servicios}>
              <Route path="nuevo" Component={ServiciosCreate} />
              <Route path=":id/editar" Component={ServiciosUpdate} />
              <Route path="" Component={ServicesTable} />
            </Route>
            <Route path="impuestos" Component={Taxes}>
              <Route path="nuevo" Component={TaxCreate} />
              <Route path=":id/editar" Component={TaxUpdate} />
              <Route path="" Component={TaxesTable} />
            </Route>
            <Route path="subscripciones" Component={Subscripciones}>
              <Route path="nuevo" Component={SubscriptionCreate} />
              <Route path=":id/editar" Component={SubscriptionUpdate} />
              <Route path="" Component={SubscriptionTable} />
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
