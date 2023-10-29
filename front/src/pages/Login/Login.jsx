import { useAuthContext } from "../../context/UseAuthContext";
import { useState } from "react";
import { useNavigate } from "react-router";

export const Login = () => {
  const { loginUser } = useAuthContext();
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(user);
    loginUser(user)
      .then(() => {
        setError(null);
        navigate("/");
      })
      .catch(() => setError("Usuario o contraseña incorrectos"));
  };

  return (
    <div className="login-container">
      <div className="login-box">
        {error && <div className="error">{error}</div>}
        <div className="avatar">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="72"
            height="72"
            fill="currentColor"
            class="bi bi-person-circle"
            viewBox="0 0 16 16"
          >
            <path d="M8 0a8 8 0 1 0 0 16 8 8 0 0 0 0-16z" />
            <path d="M8 4a2 2 0 1 0 0 4 2 2 0 0 0 0-4z" />
            <path
              fill-rule="evenodd"
              d="M8 9a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"
            />
          </svg>
        </div>
        <h1>Iniciar Sesion</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Nombre de usuario"
            name="username"
            value={user.username}
            onChange={(e) => setUser({ ...user, username: e.target.value })}
          />
          <input
            type="password"
            placeholder="Contraseña"
            name="password"
            value={user.password}
            onChange={(e) => setUser({ ...user, password: e.target.value })}
          />
          <div className="btn-login">
            <button type="submit">Ingresar</button>
          </div>
        </form>
      </div>
      <div className="copyright">&copy; Your Company 2023</div>
    </div>
  );
};
