import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import { deleteSingleUser } from "../api/UserAPI";
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

export default function DeleteModal(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const onError = (errors, e) => console.log(errors, e);
  const { register, handleSubmit, reset, control, setValue } = useForm();
  let { id, dto } = props.onClickParams;
  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleUser(id, dto));
  };

  return (
    <>
      <Button onClick={handleOpen}>{props.buttonText}</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <form onSubmit={handleSubmit(onSubmit, onError)}>
          <Box sx={style}>
            <Typography component="h1" variant="h4" align="center">
              Confirmar Eliminar Registro
            </Typography>
            <button type="submit">{props.buttonText}</button>
            <button
              onClick={async () => {
                handleClose();
              }}
            >
              cancelar
            </button>
          </Box>
        </form>
      </Modal>
    </>
  );
}
