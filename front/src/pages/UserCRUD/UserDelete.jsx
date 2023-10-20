import Box from "@mui/material/Box";
import { Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import { deleteSingleUser } from "../../api/UserAPI";
export const UserDelete = (props) => {
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };
  const onError = (errors, e) => console.log(errors, e);
  const { handleSubmit } = useForm();
  const formID = `userForm-${props.userID}`;
  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleUser(props.userID));
  };
  return (
    <form id={formID} onSubmit={handleSubmit(onSubmit, onError)}>
      <Box sx={style}>
        <Typography component="h1" variant="h4" align="center">
          Confirmar Eliminar Registro
        </Typography>
        <button form={formID} type="submit">
          Borrar
        </button>
        <button
          onClick={() => {
            props.handleClose();
          }}
        >
          cancelar
        </button>
      </Box>
    </form>
  );
};
