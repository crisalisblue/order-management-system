import "./Home.css";
import { Link as RouterLink } from "react-router-dom";
export const Home = () => {
  return (
    <div className="home-container">
      <div className="welcome-section">
        <h1 className="home-title">¡Bienvenido a Nuestro Back Office!</h1>
        <p className="home-subtitle">
          Potenciando tu gestión empresarial de manera eficiente.
        </p>
      </div>

      <div className="feature-section">
        <RouterLink className="feature" to="/clientes">
          <div className="feature-icon">&#128100;</div>
          <h2 className="feature-title">Clientes</h2>
          <p className="feature-description">Gestiona a tus clientes.</p>
        </RouterLink>

        <RouterLink className="feature" to="/productos">
          <div className="feature-icon">&#127828;</div>
          <h2 className="feature-title">Productos</h2>
          <p className="feature-description">
            Gestiona tu inventario de manera efectiva.
          </p>
        </RouterLink>

        <RouterLink className="feature" to="/servicios">
          <div className="feature-icon">&#9881;</div>
          <h2 className="feature-title">Servicios</h2>
          <p className="feature-description">
            Gestiona servicios excepcionales.
          </p>
        </RouterLink>

        <RouterLink className="feature" to="/pedidos">
          <div className="feature-icon">&#128722;</div>
          <h2 className="feature-title">Pedidos</h2>
          <p className="feature-description">
            Realiza un seguimiento de pedidos.
          </p>
        </RouterLink>
      </div>
    </div>
  );
};
