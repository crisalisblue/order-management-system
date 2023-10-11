import React from "react";
import Button from "@mui/material/Button";
import { Link as RouterLink } from "react-router-dom";
export const UserUpdate = () => {
  return (
    <div>
      <Button component={RouterLink} to="/userCRUD">
        volver
      </Button>
      <Button>actualizar</Button>
    </div>
  );
};
