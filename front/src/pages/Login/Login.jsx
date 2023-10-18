import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import "./Login.css";
import { useAuthContext } from "../../context/UseAuthContext";
import Copyright from "../../components/Copyrigth";
import { useState } from "react";
import { useNavigate } from "react-router";
import { Alert } from "@mui/material";

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
    <Container component="main" maxWidth="xs">
      <Box className="login-box">
        {error && (
          <Alert severity="error" sx={{ my: 2 }}>
            {error}
          </Alert>
        )}
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Iniciar Sesion
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Nombre de usuario"
            name="username"
            autoComplete="username"
            value={user.username}
            onChange={(e) => setUser({ ...user, username: e.target.value })}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Contraseña"
            type="password"
            id="password"
            autoComplete="current-password"
            value={user.password}
            onChange={(e) => setUser({ ...user, password: e.target.value })}
          />
          <Box className="btn-login">
            <Button type="submit" fullWidth variant="contained">
              Ingresar
            </Button>
          </Box>
        </Box>
      </Box>
      <Copyright />
    </Container>
  );
};
