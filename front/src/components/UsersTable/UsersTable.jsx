import { DataTable } from "../DataTable/DataTable";
import { getAllUsers } from "../../api/UserAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./UsersTable.css";

export const UsersTable = () => {
  const { data, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  return <DataTable data={data}></DataTable>;
};
