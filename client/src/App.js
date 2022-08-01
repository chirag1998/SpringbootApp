import React from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import './App.css';
import DataTable from './components/Datatable';
import Drawer1 from './components/Drawer';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import EmployeeDetail from './components/EmployeeDetail';

function App() {

  const SidebarLayout = () => (
    <>
      <Drawer1 />
      <Outlet />
    </>
  );

  return (
    <div className="App" style={{display: 'flex'}}>
      <Routes>
        <Route element={<SidebarLayout />}>
          <Route path="/datatable" element={<DataTable />} />
          <Route path="/employeedetail" element={<EmployeeDetail />} />
        </Route>
        <Route path="/" element={<SignIn />}></Route>
        {/* <Route path ='/dashboard' element ={<DashboardContent/>}></Route> */}
        <Route path='/signup' element={<SignUp />}></Route>
      </Routes>
    </div>
  );
}

export default App;
