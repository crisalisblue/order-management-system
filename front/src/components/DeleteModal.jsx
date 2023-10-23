import React from "react";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { UserDelete } from "../pages/Usuarios/UserDelete";

export const DeleteModal = ({ userID }) => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  return (
    <>
      <Button onClick={handleOpen}>Borrar</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <>
          <UserDelete userID={userID} handleClose={handleClose}></UserDelete>;
        </>
      </Modal>
    </>
  );
};
