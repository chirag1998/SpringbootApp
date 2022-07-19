import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Grid } from '@mui/material';

export default function AddDialog({open1, handleClose,data, onChange, handleFormSubmit}) {
    const{id, firstName, lastName, email, date}= data;
    
    return (
        <div>
            <Dialog open={open1} onClose={handleClose}>
                <DialogTitle>{id?"Update Employee":"Add Employee"}</DialogTitle>
                <DialogContent>
                    <form>
                        <Grid container spacing={4}>
                            <Grid item xs={6}><TextField
                            autoFocus
                            autoComplete='off'
                            margin="dense"
                            id="firstName"
                            label="First Name"
                            fullWidth
                            variant="outlined"
                            size="small"
                            value={firstName}
                            onChange = {e=>onChange(e)}
                        />
                        </Grid>
                        <Grid item xs={6}>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="lastName"
                            label="Last Name"
                            fullWidth
                            variant="outlined"
                            size="small"
                            value={lastName}
                            onChange = {e=>onChange(e)}
                        />
                        </Grid>
                        
                        </Grid>
                        
                        <TextField
                            autoFocus
                            margin="dense"
                            id="email"
                            label="Email"
                            fullWidth
                            variant="outlined"
                            size="small"
                            type="email"
                            value={email}
                            onChange = {e=>onChange(e)}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="date"
                            label = "date of birth"
                            fullWidth
                            variant="outlined"
                            size="small"
                            type="date"
                            value={date}
                            onChange = {e=>onChange(e)}
                        />
                    </form>
                </DialogContent>
                <DialogActions>
                    <Button color="secondary" variant='outlined' onClick={handleClose}>Cancel</Button>
                    <Button onClick={()=>handleFormSubmit()} variant='outlined'>{id?"Update":"Add"}</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
