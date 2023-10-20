import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function createData(id, nombre, direccion, empresa, total) {
    return { id, nombre, direccion, empresa, total };
}

const rows = [
    createData(1, "Martin Perez", "Sarmiento 2133, Buenos Aires", "Claro", 13550.00),
    createData(2, "Jorge Perez", "Junin 133, Catamarca", "Royal", 44121.30),
    createData(3, "Lucas Perez", "Peron 2153, Buenos Aires", "Movistar", 61244.00),
    createData(4, "Santiago Perez", "Paraguay 3237, Buenos Aires", "Adidas", 412412.30),
    createData(5, "Ricardo Perez", "Santa Fé 2003, Cordoba", "La Serensima", 312400.90),
];

const style = {
    maxWidth: 600,
    ml: 2,
};

export const HomeTable = () => {
    return (
        <TableContainer component={Paper} sx={style}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell align="right">NOMBRE</TableCell>
                        <TableCell align="right">DIRECCION</TableCell>
                        <TableCell align="right">EMPRESA</TableCell>
                        <TableCell align="right">TOTAL</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow
                            key={row.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">
                                {row.id}
                            </TableCell>
                            <TableCell align="right">{row.nombre}</TableCell>
                            <TableCell align="right">{row.direccion}</TableCell>
                            <TableCell align="right">{row.empresa}</TableCell>
                            <TableCell align="right">${row.total}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};