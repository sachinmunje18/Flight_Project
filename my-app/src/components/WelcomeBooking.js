import React from 'react';
import { useNavigate } from 'react-router-dom';
import './WelcomeBooking.css';
import AppNavbar from "./Navbar"
import './Dashboard.css';

function WelcomeBooking() {
    const navigate = useNavigate();

    const handleHome = () => {
        navigate('/dashboard');
    };

    const handleViewBookings = () => {
        navigate('/view-booking');
    };
    const handleLogout = () => {
        // toast.success("Logout Successfully");
        navigate('/');
      };

    return (
        <>
         <nav className="navbar">
          <div className="navbar-brand">Flight Booking</div>
          <ul className="navbar-menu">
           
            <li className="navbar-item"><a onClick={handleLogout}>Logout</a></li>
          </ul>
        </nav>
        <div className="welcome-booking-container">
            <h1>Thank You for Booking!</h1>
            <p>Your booking was successful. We look forward to seeing you on your trip.</p>
           <div className='btn-wrap'>
           <button onClick={handleHome}>Create New Booking</button>
           <button onClick={handleViewBookings}>See Your Bookings</button>
           </div>
        </div>
        </>
        
    );
}

export default WelcomeBooking;
