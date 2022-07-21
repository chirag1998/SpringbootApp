import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { Button, Grid, IconButton, TextField } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from 'axios';
import AddDialog from './AddDialog';
import CustomSnackbar from './CustomSnackbar';

const DATA_LIST_URL = "http://localhost:8080/";
const ADD_DATA = "http://localhost:8080/addemployee";
const initialValue = { firstName: "", lastName: "", email: "", date: "" }

export default function DataTable() {

  const [data, setData] = React.useState("");
  const [open1, setOpen1] = React.useState(false);
  const [sopen, setSopen] = React.useState(false);
  const [smessage, setSmessage] = React.useState("");
  const [serror, setSerror] = React.useState("");
  const [formData, setFormData] = React.useState(initialValue)

  const onChange = (e) => {
    const { value, id } = e.target
    //console.log(e);
    setFormData({ ...formData, [id]: value })
  }

  const handleFormSubmit = () => {
    if (formData.id) {
      //console.log("input ", formData.id)
      //let id = formData.id;
      axios.put(DATA_LIST_URL + 'update/' + formData.id, formData)
        .then(resp => {console.log(resp.status)
          if(resp.status === 200){
            setSopen(true);
            setSmessage("Updated Successfully");
            setSerror("success");
            handleClose();
            refetch();
          }
        }).catch((error)=>{
          console.log("hi")
            console.log(error)
            setSopen(true);
            setSmessage("Not able to Update");
            setSerror("error");
            handleClose();
        })
      
      //setData(data.filter(data => data.id !== id));
      setFormData(initialValue);
    }
    else {
      axios.post(ADD_DATA, formData)
        //.then(resp => resp.json())
        .then(resp => {console.log(resp.status)
        if(resp.status === 201){
            setSopen(true);
            setSmessage("Updated Successfully");
            setSerror("success");
            handleClose();
            refetch();
        }}).catch((error)=>{
          console.log(error)
          setSopen(true);
          setSmessage("Not able to Add");
          setSerror("error");
          handleClose();
        })
      
      setFormData(initialValue);
    }

  }

  const handleSclose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setSopen(false);
  } 

  const handleClickOpen = () => {
    setOpen1(true);
  };

  const handleClose = () => {
    setOpen1(false);
    setFormData(initialValue);
  };

  const handleDelete = (id) => {
    axios.delete(DATA_LIST_URL + 'delete/' + id)
      .then(resp => console.log(resp))

    setData(data.filter(data => data.id !== id));
  }

  const handleClick = (cellValues) => {
    console.log(cellValues)
    setFormData(cellValues);
    handleClickOpen();
  }

  const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'firstName', headerName: 'First name', width: 120 },
    { field: 'lastName', headerName: 'Last name', width: 120 },
    {
      field: 'email', headerName: 'E-Mail', width: 250,
    },
    {
      field: 'date', headerName: 'Date', width: 120,
    },
    {
      field: 'edit', headerName: 'Edit', sortable: false,
      renderCell: (cellValues) => {
        return (
          <IconButton
            onClick={() => handleClick(cellValues.row)}><EditIcon /></IconButton>
          //<FormDialog />
        );
      },
      width: 100
    },
    {
      field: 'delete', headerName: 'Delete', sortable: false,
      renderCell: (cellValues) => {
        return (
          <IconButton
            onClick={() => handleDelete(cellValues.id)}><DeleteIcon /></IconButton>
        );
      }
    },

  ];

  function refetch() {
    // axios.get(DATA_LIST_URL + 'listemployee')
    //   .then(resp => {
    //     console.log(resp.data)
    //     setData(resp.data)
    //   })
    fetchData();
  }

  // React.useEffect(() => {
  //   axios.get(DATA_LIST_URL + 'pagablelist/'+page)
  //     .then(resp => {
  //       console.log(resp.data.content)
  //       setTotal(resp.data.totalElements)
  //       setData(resp.data)
  //     })
  // }, [])
  const [page, setPage] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(false);
  const [pageSize, setPageSize] = React.useState([6]);
  const [total, setTotal] = React.useState(0);

  React.useEffect(()=>{
    fetchData();
  },[page])

  const fetchData = async () =>{
    setIsLoading(true);
    const response = await axios.get(DATA_LIST_URL+'pagablelist/'+page)
    console.log(response.data.content);
    setData(response.data.content)
    setTotal(response.data.totalElements);
    console.log(response.data.totalElements)
    setIsLoading(false);
  }

  return (
    <>
      
      <Grid align="right" sx={{ pb: "10px" }}>
        <TextField size='small' variant='standard' label="Search" color='primary' sx={{mr : '20px', mt:'-8px'}}></TextField>
        <Button variant='outlined' onClick={handleClickOpen}>ADD EMPLOYEE</Button>
      </Grid>
      <AddDialog open1={open1} handleClose={handleClose} data={formData} onChange={onChange}
        handleFormSubmit={handleFormSubmit} />
      <div style={{ height: 425, width: '100%' }}>
        <DataGrid
          rows={data}
          columns={columns}
          rowCount={total}
          loading = {isLoading}
          rowsPerPageOptions={[6]}
          pagination
          page = {page}
          pageSize={pageSize}
          paginationMode="server"
          onPageChange={(newPage) => {
            setPage(newPage)
            //console.log(newPage);
          }}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          checkboxSelection
          sx={{ pl: '24px' }}
          disableColumnMenu
          disableSelectionOnClick
        />
      </div>
      <CustomSnackbar message={smessage} severity={serror} sopen={sopen} onClose={handleSclose}/>
    </>

  );
}
