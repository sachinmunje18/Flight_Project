import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AuthPage from './components/AuthPage';
import Dashboard from './components/Dashboard';
import WelcomeBooking from './components/WelcomeBooking';
import ViewBooking from './components/ViewBooking';
import UpdateTicket from './components/UpdateTicket';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<AuthPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/welcome-booking" element={<WelcomeBooking />} />
        <Route path="/view-booking" element={<ViewBooking />} />
        <Route path="/update-ticket/:id" element={<UpdateTicket />} />
      </Routes>
    </Router>
  );
}

export default App;
