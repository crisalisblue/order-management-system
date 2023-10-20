import { DataGrid } from "@mui/x-data-grid";
import { DeleteModal } from "./DeleteModal";
import Button from "@mui/material/Button";

import { Link as RouterLink } from "react-router-dom";
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
      width: 230,
      description: "This column has a value getter and is not sortable.",
      sortable: false,
      renderCell: (params) => {
        return (
          <>
            <DeleteModal userID={params.row.id}></DeleteModal>
            <Button
              component={RouterLink}
              to={`/userCRUD/${params.row.id}/editar`}
            >
              actualizar
            </Button>
          </>
        );
      },
    },
  ];

  return (
    <div style={{ height: 400, width: "100%" }}>
      <DataGrid
      getRowId={props.id}
        rows={Array.from(JSON.parse(props.data))}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
        disableRowSelectionOnClick
      />
    </div>
  );
}
