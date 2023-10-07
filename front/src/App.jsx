import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import { Home } from "./pages/Home.jsx";
import  UserCRUD  from "./pages/UserCRUD.jsx";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" Component={Home}></Route>
        <Route path="/userCRUD" Component={UserCRUD}></Route>
      </Routes>
    </Router>
  );
}
