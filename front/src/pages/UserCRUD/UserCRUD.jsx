import { Typography } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import Button from "@mui/material/Button";
import { Outlet } from "react-router-dom";
import { Link as RouterLink } from "react-router-dom";

export const UserCRUD = () => {
  return (
    <>
      <Grid
        container
        component="section"
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <Typography component="h1" variant="h4" align="center">
          Gestor de Usuarios
        </Typography>
      </Grid>
      <Button component={RouterLink} to="/userCRUD">
        Lista de Usuarios
      </Button>
      <Button component={RouterLink} to="/userCRUD/nuevo">
        Crear Usuario
      </Button>
      <Outlet />
    </>
  );
};
