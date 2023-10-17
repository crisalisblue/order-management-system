import DataTable from "../../components/DataTable";
import Grid from "@mui/material/Unstable_Grid2";
import { getAllUsers } from "../../api/UserAPI";
import { useDataFetching } from "../../api/UserAPI_Utils";
export const UsersTable = () => {
  const { data, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return (
    <Grid component="article" container spacing={1}>
      <Grid component="section" xs={12}>
        {loading ? (
          "loading..."
        ) : (
          <DataTable data={JSON.stringify(data, null)}></DataTable>
        )}
      </Grid>
    </Grid>
  );
};
