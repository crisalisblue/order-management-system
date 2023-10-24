import DataTable from "../../components/DataTable";
import { getAllUsers } from "../../api/UserAPI";
import { useDataFetching } from "../../api/UserAPI_Utils";
export const UsersTable = () => {
  const { data, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return <div>UserTable</div>;
};
