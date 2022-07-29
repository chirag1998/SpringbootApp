import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import DashboardIcon from '@mui/icons-material/Dashboard';
import BarChartIcon from '@mui/icons-material/BarChart';
import LayersIcon from '@mui/icons-material/Layers';
import ListAltIcon from '@mui/icons-material/ListAlt';
import { useNavigate } from 'react-router-dom';



export default function Listitems1({roles}) {

    let navigate = useNavigate();

    const [role, setrole] = React.useState([]);

    React.useEffect(() => {
      //console.log("from list", roles)
      setrole(roles);
    }, [roles])

    
    
    return (
        <React.Fragment>
            {/* <ListItemButton onClick={() => navigate(`add-employee`)}> */}
            <ListItemButton>
                <ListItemIcon>
                    <DashboardIcon />
                </ListItemIcon>
                <ListItemText primary="Dashboard" />
            </ListItemButton>
            <ListItemButton>
                <ListItemIcon>
                    <ListAltIcon />
                </ListItemIcon>
                <ListItemText primary="Tab 1" />
            </ListItemButton>
            <ListItemButton onClick={() => navigate('/tab2')}>
                <ListItemIcon>
                    <BarChartIcon />
                </ListItemIcon>
                <ListItemText primary="Tab 2" />
            </ListItemButton>
            {role.includes('Admin') &&
            <ListItemButton>
                <ListItemIcon>
                    <LayersIcon />
                </ListItemIcon>
                <ListItemText primary="Tab 3" />
            </ListItemButton>}
        </React.Fragment>
    );
}

