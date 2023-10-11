import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import Modal from "@mui/material/Modal";
import Input from "@mui/material/Input";
import InputLabel from "@mui/material/InputLabel";
import FormHelperText from "@mui/material/FormHelperText";

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

export default function ButtonModal(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password"),
    });
  };
  let { id, dto } = props.onClickParams;
  return (
    <div>
      <Button onClick={handleOpen}>{props.buttonText}</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <FormControl>
            <InputLabel htmlFor="name-input">Name</InputLabel>
            <Input id="name-input" aria-describedby="my-helper-text" />
            <FormHelperText id="my-helper-text">
              We'll never share your Name.
            </FormHelperText>
          </FormControl>
          <FormControl>
            <InputLabel htmlFor="username-input">Username</InputLabel>
            <Input id="username-input" aria-describedby="my-helper-text" />
            <FormHelperText id="my-helper-text">
              We'll never share your Username.
            </FormHelperText>
          </FormControl>
          <FormControl>
            <InputLabel htmlFor="password-input">Password</InputLabel>
            <Input id="password-input" aria-describedby="my-helper-text" />
            <FormHelperText id="my-helper-text">
              We'll never share your Password.
            </FormHelperText>
          </FormControl>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Submit
          </Button>
          {/*falta trycatch y/o manejo de errores mas limpio tras limpiar api*/}
          <button
            onClick={async () => {
              console.dir(id);
              console.dir(dto);
              console.dir(props.onClick(id, dto));
              console.dir(typeof props.onClick(id, dto));
              console.dir(await props.onClick(id, dto));
            }}
          >
            {props.buttonText}
          </button>
        </Box>
      </Modal>
    </div>
  );
}
