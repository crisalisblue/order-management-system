import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import { Home } from "./pages/Home.jsx";
// import  UserCRUD  from "./pages/UserCRUD.jsx";
import Login from "./pages/Login";
import Layout from "./pages/Layout/Layout";
import NotLogedinRoute from "./routes/NotLogedinRoute";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" Component={Layout}>
          <Route path="/home" Component={Home} />
          {/* <Route path="/userCRUD" Component={UserCRUD}></Route> */}
        </Route>
        <Route
          path="/login"
          element={
            <NotLogedinRoute>
              <Login />
            </NotLogedinRoute>
          }
        />
      </Routes>
    </Router>
  );
}
