import * as React from "react";
import { DataGrid } from "@mui/x-data-grid";

export default function DataTable(props) {
  const columns = [
    { field: "id", headerName: "ID", width: 100 },
    { field: "name", headerName: "Name", width: 130 },
    { field: "username", headerName: "username", width: 130 },
    { field: "password", headerName: "Password", width: 130 },
    {
      field: "actions",
      value: null,
      headerName: "Actions",
      width: 200,
      description: "This column has a value getter and is not sortable.",
      sortable: false,
      renderCell: (params) => {
        return (
          <>
            <button
              onClick={async () => {
                try {
                  const result = await props.singleUser(params.row.id);
                  console.log(result); // The result of the promise
                } catch (error) {
                  console.error(error);
                }
              }}
            >
              Editar
            </button>

            <button
              onClick={async () => {
                try {
                  const result = await props.deleteUser(params.row.id);
                  console.log(result); // The result of the promise
                } catch (error) {
                  console.error(error);
                }
              }}
            >
              Borrar
            </button>
            <button
              onClick={async () => {
                try {
                  const result = await props.updateUser(params.row.id, {
                    test: "test",
                  });
                  console.log(result); // The result of the promise
                } catch (error) {
                  console.error(error);
                }
              }}
            >
              Update
            </button>
          </>
        );
      },
    },
  ];


  return (
    <div style={{ height: 400, width: "100%" }}>
      <DataGrid
        getRowId={(row) => row.id}
        rows={Array.from(JSON.parse(props.data))}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
      />
    </div>
  );
}
