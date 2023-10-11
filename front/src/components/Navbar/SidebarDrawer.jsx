import Box from "@mui/material/Box";
import {Divider, List, ListItem, ListItemButton, ListItemText} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";

export const SidebarDrawer = () => {
  return (
      <Box height={"100%"}>
        <nav style={{height: "100%"}}>
            <List>
                <ListItem component={RouterLink} to="/home">
                    <ListItemText primary="Home"/>
                </ListItem>
                <ListItem component={RouterLink} to="/pedidos">
                    <ListItemText primary="Pedidos"/>
                </ListItem>
                <ListItem component={RouterLink} to="/servicios">
                    <ListItemText primary="Servicios"/>
                </ListItem>
                <ListItem component={RouterLink} to="/productos">
                    <ListItemText primary="Productos"/>
                </ListItem>
                <ListItem component={RouterLink} to="/clientes">
                    <ListItemText primary="Clientes"/>
                </ListItem>
                <ListItem component={RouterLink} to="/impuestos">
                    <ListItemText primary="Impuestos"/>
                </ListItem>
                <ListItem component={RouterLink} to="/userCRUD">
                    <ListItemText primary="Usuarios"/>
                </ListItem>
                <Divider/>
                <ListItem disablePadding>
                    <ListItemButton component={RouterLink} to="/login">
                        <ListItemText primary="Cerrar Sesion"/>
                    </ListItemButton>
                </ListItem>
            </List>


        </nav>
      </Box>
  )
};