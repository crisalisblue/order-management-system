import * as React from "react";
import { DataGrid } from "@mui/x-data-grid";
import ButtonModal from "./ButtonModal";
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
            <ButtonModal
              onClick={props.singleUser}
              buttonText="Leer"
              onClickParams={{ id: params.row.id }}
            ></ButtonModal>
            <ButtonModal
              onClick={props.deleteUser}
              buttonText="Borrar"
              onClickParams={{ id: params.row.id }}
            ></ButtonModal>
            <ButtonModal
              onClick={props.updateUser}
              onClickParams={{
                id: params.row.id,
                dto: { password: "test2PasswordModifi2cado" },
              }}
              buttonText="Update"
            ></ButtonModal>
          </>
        );
      },
    },
  ];

  return (
    <div style={{ height: 400, width: "100%" }}>
      <DataGrid
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
