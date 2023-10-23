import DataTable from "../../components/DataTable";
import Grid from "@mui/material/Unstable_Grid2";
import { getAllUsers } from "../../api/UserAPI";
import { useDataFetching } from "../../api/UserAPI_Utils";
export const UsersTable = () => {
  const { data, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return <div>UserTable</div>;
};
