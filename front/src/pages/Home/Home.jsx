import "./Home.css";

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
        <div className="feature">
          <div className="feature-icon">&#128100;</div>
          <h2 className="feature-title">Clientes</h2>
          <p className="feature-description">Gestiona a tus clientes.</p>
        </div>

        <div className="feature">
          <div className="feature-icon">&#127828;</div>
          <h2 className="feature-title">Productos</h2>
          <p className="feature-description">
            Gestiona tu inventario manera efectiva.
          </p>
        </div>

        <div className="feature">
          <div className="feature-icon">&#9881;</div>
          <h2 className="feature-title">Servicios</h2>
          <p className="feature-description">
            Gestiona servicios excepcionales.
          </p>
        </div>

        <div className="feature">
          <div className="feature-icon">&#128722;</div>
          <h2 className="feature-title">Pedidos</h2>
          <p className="feature-description">
            Realiza un seguimiento de pedidos.
          </p>
        </div>
      </div>
    </div>
  );
};
