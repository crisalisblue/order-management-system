import { DataGrid } from "@mui/x-data-grid";
import { DeleteModal } from "../DeleteModal";
import Button from "@mui/material/Button";
import { Link as RouterLink } from "react-router-dom";

import "./DataTable.css";
export default function DataTable(props) {
  const columns = [
    { field: "name", headerName: "Name", flex: 1 },
    { field: "username", headerName: "username", flex: 1 },
    { field: "password", headerName: "Password", flex: 1 },
    {
      field: "actions",
      value: null,
      headerName: "Actions",

      description: "This column has a value getter and is not sortable.",
      sortable: false,
      flex: 1,
      renderCell: (params) => {
        return (
          <>
            <DeleteModal userID={params.row.id}></DeleteModal>
            <Button
              component={RouterLink}
              to={`/usuarios/${params.row.id}/editar`}
            >
              editar
            </Button>
          </>
        );
      },
    },
  ];

  return (
    <div style={{ height: 400, margin: "1rem" }}>
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
        getRowClassName={(params) =>
          params.indexRelativeToCurrentPage % 2 === 0 ? "even" : "odd"
        }
      />
    </div>
  );
}
