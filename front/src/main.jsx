import { createRoot } from "react-dom/client";
import App from "./App";
import AuthProvider from "./context/AuthProvider";
import React from "react";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);
root.render(
  <AuthProvider>
    <App />
  </AuthProvider>
);
