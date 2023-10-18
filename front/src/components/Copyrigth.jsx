import { Typography } from "@mui/material";
import { Box } from "@mui/system";

const Copyright = (props) => {
  return (
    <Box className="copyright">
      <Typography
        variant="body2"
        color="text.secondary"
        align="center"
        {...props}
      >
        {"Copyright © "}
        Finnegans Equipo Blue {new Date().getFullYear()}
      </Typography>
    </Box>
  );
};

export default Copyright;
