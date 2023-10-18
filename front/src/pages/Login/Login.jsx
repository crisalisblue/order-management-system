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

export const Login = () => {

  const {loginUser} = useAuthContext();
  const [user, setUser] = useState({
    username: "",
    password: ""
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      username: data.get("username"),
      password: data.get("password"),
    });
    // setUser(data.get("email"),)
    loginUser()
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box className="login-box">
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
            autoFocus
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="contraseña"
            label="Contraseña"
            type="password"
            id="password"
            autoComplete="current-password"
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
