import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import { Home } from "./pages/Home/Home.jsx";
import { UserCRUD } from "./pages/UserCRUD/UserCRUD.jsx";
import { Login } from "./pages";
import { Layout } from "./pages/Layout/Layout";
import { UserCreate } from "./pages/UserCRUD/UserCreate";
import { UsersTable } from "./pages/UserCRUD/UsersTable";
import { UserUpdate } from "./pages/UserCRUD/UserUpdate";
// import NotLogedinRoute from "./routes/NotLogedinRoute";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" Component={Layout}>
          <Route path="home" Component={Home} />
          <Route path="userCRUD" Component={UserCRUD}>
            <Route path="nuevo" Component={UserCreate} />
            <Route path=":id/editar" Component={UserUpdate} />
            <Route path="" Component={UsersTable} />
          </Route>
        </Route>
          <Route path="/login" Component={Login}/>
        {/* <Route Component={NotLogedinRoute}>
        </Route> */}
      </Routes>
    </Router>
  );
}
