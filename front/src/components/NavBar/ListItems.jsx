import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import AssignmentIcon from '@mui/icons-material/Assignment';
import {Construction, ContentPaste, Home, Logout} from "@mui/icons-material";
import {Link as RouterLink} from "react-router-dom";

export const mainListItems = (
    <React.Fragment>
        <ListItemButton component={RouterLink} to="/home">
            <ListItemIcon>
                <Home/>
            </ListItemIcon>
            <ListItemText primary="Inicio" />
        </ListItemButton>
        <ListItemButton component={RouterLink} to="/pedidos">
            <ListItemIcon>
                <ContentPaste/>
            </ListItemIcon>
            <ListItemText primary="Pedidos" />
        </ListItemButton>
        <ListItemButton component={RouterLink} to="/clientes">
            <ListItemIcon>
                <PeopleIcon />
            </ListItemIcon>
            <ListItemText primary="Clientes" />
        </ListItemButton>
        <ListItemButton component={RouterLink} to="/productos">
            <ListItemIcon>
                <ShoppingCartIcon />
            </ListItemIcon>
            <ListItemText primary="Productos" />
        </ListItemButton>
        <ListItemButton component={RouterLink} to="/servicios">
            <ListItemIcon>
                <Construction />
            </ListItemIcon>
            <ListItemText primary="Servicios" />
        </ListItemButton>
        <ListItemButton component={RouterLink} to="/impuestos">
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Impuestos" />
        </ListItemButton>
    </React.Fragment>
);

export const secondaryListItems = (
    <React.Fragment>
        <ListItemButton component={RouterLink} to="/userCRUD">
            <ListItemIcon>
                <PeopleIcon />
            </ListItemIcon>
            <ListItemText primary="Usuarios" />
        </ListItemButton>

        <ListItemButton component={RouterLink} to="/login">
            <ListItemIcon>
                <Logout/>
            </ListItemIcon>
            <ListItemText primary="Cerrar Sesion" />
        </ListItemButton>
    </React.Fragment>
);