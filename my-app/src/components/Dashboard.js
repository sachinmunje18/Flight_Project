import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Modal, Button } from 'react-bootstrap';
import './Dashboard.css';

function Dashboard() {
  const [source, setSource] = useState('');
  const [destination, setDestination] = useState('');
  const [date, setDate] = useState('');
  const [flights, setFlights] = useState([]);
  const [message, setMessage] = useState('');
  const [selectedFlight, setSelectedFlight] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [isBooking, setIsBooking] = useState(false); // Prevent duplicate bookings
  const navigate = useNavigate();

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get('http://localhost:8080/api/flights/search', {
        params: { source, destination, date }
      });

      if (response.data.length > 0) {
        setFlights(response.data);
        setMessage('');
      } else {
        setFlights([]);
        setMessage('No flights available on this date.');
      }
    } catch (error) {
      console.error('Error fetching flights:', error);
      setFlights([]);
      setMessage('An error occurred while fetching flights.');
    }
  };

  const handleBook = async (flight) => {
    if (isBooking) return; // Prevent multiple submissions
    
    setIsBooking(true);
    
    try {
      await axios.post('http://localhost:8080/api/search/save', {
        source: flight.source,
        destination: flight.destination,
        date: flight.date
      });

      setSelectedFlight(flight);
      setShowModal(true);
    } catch (error) {
      console.error('Error during booking:', error);
      toast.error('An error occurred while booking the flight.');
    } finally {
      setIsBooking(false);
    }
  };

  const handleClose = () => setShowModal(false);

  const handleLogout = () => {
    toast.success("Logout Successfully");
    navigate('/');
  };

  const gotomanageBookings = () => {
    navigate('/view-booking');
  };

  return (
    <>
      <ToastContainer />
      <div className="dashboard-container">
        <nav className="navbar">
          <div className="navbar-brand">Flight Booking</div>
          <ul className="navbar-menu">
            <li className="navbar-item"><a onClick={gotomanageBookings}>View Your Bookings</a></li>
            <li className="navbar-item"><a onClick={handleLogout}>Logout</a></li>
          </ul>
        </nav>

        <div className="dashboard-content">
          <div className="search-form">
            <h2>Search Flights</h2>
            <form onSubmit={handleSearch}>
              <div className="form-group">
                <label>Source</label>
                <input
                  type="text"
                  value={source}
                  onChange={(e) => setSource(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label>Destination</label>
                <input
                  type="text"
                  value={destination}
                  onChange={(e) => setDestination(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label>Date</label>
                <input
                  type="date"
                  value={date}
                  onChange={(e) => setDate(e.target.value)}
                  required
                />
              </div>
              <div>
                <button type="submit" className='search-btn'>Search</button>
              </div>
            </form>
          </div>

          {flights.length > 0 ? (
            <div className="flights-list">
              <h4>Available Flights</h4>
              <ul>
                {flights.map((flight) => (
                  <div className="fightcount" key={flight.id}>
                    <li> <span className="counhead">Flight Number</span> <span className="countDeatils">{flight.flightNumber}</span></li>
                    <li> <span className="counhead">Source</span> <span className="countDeatils">{flight.source}</span></li>
                    <li> <span className="counhead">Destination</span> <span className="countDeatils">{flight.destination}</span></li>
                    <li> <span className="counhead">Date</span> <span className="countDeatils">{flight.date}</span></li>
                    <li> <span className="counhead">Cost</span> <span className="countDeatils">₹{flight.cost}</span></li>
                    <li> <button className="booknowbtn" onClick={() => handleBook(flight)}>Book</button></li>
                  </div>
                ))}
              </ul>
            </div>
          ) : (
            message && <p>{message}</p>
          )}
        </div>
      </div>

      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Booking Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Thank You for Booking!</h4>
          <p>Your booking for Flight Number: {selectedFlight?.flightNumber} was successful.</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={() => navigate('/welcome-booking')}>OK</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Dashboard;
