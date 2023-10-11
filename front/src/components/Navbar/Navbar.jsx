import {SidebarDrawer} from "./SidebarDrawer.jsx";
import Button from "@mui/material/Button";
import {useState} from "react";
import {Drawer} from "@mui/material";

export const Navbar = () => {
    const [open, setOpen] = useState(false)
    return (
        <>

            <Button onClick={() => setOpen(true)}>Menu</Button>
            <Drawer open={open} anchor={"left"} onClose={() => setOpen(false)}>
                <SidebarDrawer/>
            </Drawer>

        </>
    )
};