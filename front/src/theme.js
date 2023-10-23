import { createTheme } from "@mui/material/styles";
import { red } from "@mui/material/colors";

// Create a theme instance.
const theme = createTheme({
  palette: {
    mode: "light",
    primary: {
      main: "#3f51b5",
    },
    secondary: {
      main: "#f50057",
    },
    error: {
      main: red.A400,
    },
  },
  overrides: {
    MuiDataGrid: {
      root: {
        borderColor: 'red',
        borderRadius: 0,
        borderWidth: '1px 0 0',
      },
    },
  },
});

export default theme;
