import React from "react";
import Button from "@mui/material/Button";
import { Link as RouterLink } from "react-router-dom";
export const UserCreate = () => {
  return (
    <div>
      <Button component={RouterLink} to="/userCRUD">
        volver
      </Button>
      <Button>crear</Button>
    </div>
  );
};
