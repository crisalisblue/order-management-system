import { DataTable } from "../DataTable/DataTable";
import { getAllUsers, deleteSingleUser } from "../../api/UserAPI";
import { useDataFetching } from "../../api/API_Utils";
import "./UsersTable.css";

export const UsersTable = () => {
  const { data: usuariosData, loading, error } = useDataFetching(getAllUsers);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  if (!usuariosData) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={usuariosData}
      keysToShow={Object.keys(...usuariosData)}
      itemName="usuario"
      editPath="/usuarios"
      deleteFunction={deleteSingleUser}
    />
  );
};
