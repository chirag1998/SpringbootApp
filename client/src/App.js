import React from 'react';
import { Routes,Route } from 'react-router-dom';
import './App.css';
import DashboardContent from './components/Dashboard';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path = "/" element = { <SignIn />}></Route>
        <Route path ='/dashboard' element ={<DashboardContent/>}></Route>
        <Route path ='/signup' element ={<SignUp/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
