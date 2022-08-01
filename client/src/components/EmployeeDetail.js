import { Box, Toolbar } from '@mui/material'
import React from 'react'

export default function EmployeeDetail() {
  
    return (
        <div>
            <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
            flexGrow: 1,
            height: '100vh',
            overflow: 'auto',
          }}
        >
            <Toolbar />
            EmployeeDetail
            </Box>
            </div>
    )
}
