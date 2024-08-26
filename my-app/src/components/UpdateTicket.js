import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './UpdateTicket.css';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function UpdateTicket() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [source, setSource] = useState('');
  const [destination, setDestination] = useState('');
  const [date, setDate] = useState('');
  const [originalSource, setOriginalSource] = useState('');
  const [originalDestination, setOriginalDestination] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchSearchQuery = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/search/${id}`);
        const query = response.data;
        setSource(query.source);
        setDestination(query.destination);
        setDate(query.date);
        setOriginalSource(query.source);
        setOriginalDestination(query.destination);
      } catch (error) {
        console.error('Error fetching search query:', error);
        toast.error('An error occurred while fetching the search query.');
      }
    };

    fetchSearchQuery();
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    if (!source || !destination || !date) {
      toast.error('Please fill in all fields.');
      return;
    }

    try {
      const flightResponse = await axios.get('http://localhost:8080/api/flights/search', {
        params: { source, destination, date }
      });

      if (flightResponse.data.length > 0) {
        await axios.put(`http://localhost:8080/api/search/${id}`, {
          source,
          destination,
          date
        });
        toast.success('Booking updated successfully.');

        // Delay redirection to allow toast message to be visible
        setTimeout(() => {
          navigate('/dashboard');
        }, 2000); // Adjust the delay as needed (2000 ms = 2 seconds)
        
        setError('');
      } else {
        toast.error('No matching flights available for the provided source, destination, and date.');
        setError('');
      }
    } catch (error) {
      console.error('Error updating search query:', error);
      toast.error('An error occurred while updating the search query.');
      setError('');
    }
  };

  const handleGoToDashboard = () => {
    navigate('/dashboard');
  };

  const handleLogout = () => {
    toast.success('Logout Successfully');
    navigate('/');
  };

  return (
    <>
      <ToastContainer />
      <nav className="navbar">
        <div className="navbar-brand">Flight Booking</div>
        <ul className="navbar-menu">
          <li className="navbar-item"><a onClick={handleGoToDashboard}>Create New Booking</a></li>
          <li className="navbar-item"><a onClick={handleLogout}>Logout</a></li>
        </ul>
      </nav>
      <div className="update-ticket-container">
        <h2>Update Booking Details</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleUpdate}>
          <div className="form-group">
            <label htmlFor="source">Source:</label>
            <input
              type="text"
              id="source"
              value={source}
              onChange={(e) => setSource(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="destination">Destination:</label>
            <input
              type="text"
              id="destination"
              value={destination}
              onChange={(e) => setDestination(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="date">Date:</label>
            <input
              type="date"
              id="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              required
            />
          </div>
          <button type="submit" className='btn-update'>Update</button>
        </form>
      </div>
    </>
  );
}

export default UpdateTicket;
//