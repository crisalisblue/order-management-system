import React from "react";
import { Typography } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import DataTable from "../components/DataTable";
import {
  getAllUsers,
  getSingleUser,
  deleteSingleUser,
  updateSingleUser,
} from "../api/UserAPI";
import { useDataFetching } from "../api/UserAPI_Utils";
export const UserCRUD = () => {
  const { data, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return (
    <Grid
      container
      component="main"
      direction="column"
      justifyContent="center"
      alignItems="center"
      height="100dvh"
    >
      <Typography component="h1" variant="h4" align="center">
        Gestor de Usuarios
      </Typography>
      <Grid component="article" container spacing={1}>
        <Grid component="section" xs={12}>
          {loading ? (
            "loading..."
          ) : (
            <DataTable
              singleUser={getSingleUser}
              deleteUser={deleteSingleUser}
              updateUser={updateSingleUser}
              data={JSON.stringify(data, null)}
            ></DataTable>
          )}
        </Grid>
      </Grid>
    </Grid>
  );
};

export default UserCRUD;
