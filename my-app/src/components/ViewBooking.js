import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Container, Row, Col, Table, Button, Alert, Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './ViewBooking.css';
import AppNavbar from './Navbar';
import './Dashboard.css';

function ViewBooking() {
  const [searchQueries, setSearchQueries] = useState([]);
  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState(''); // State for success message
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSearchQueries = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/search/all');
        setSearchQueries(response.data);
      } catch (error) {
        console.error('Error fetching search queries:', error);
        setError('An error occurred while fetching data.');
      }
    };

    fetchSearchQueries();
  }, []);

  const handleUpdate = (id) => {
    navigate(`/update-ticket/${id}`);
  };

  const handleLogout = () => {
    navigate('/');
  };

  const handleHome = () => {
    navigate('/dashboard');
  };

  const handleCancel = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/search/${id}`);
      setSearchQueries(searchQueries.filter(query => query.id !== id));
      setSuccessMessage('Booking canceled successfully.'); // Set success message
    } catch (error) {
      console.error('Error deleting search query:', error);
      setError('An error occurred while deleting the search query.');
    }
  };

  return (
    <>
      <nav className="navbar">
        <div className="navbar-brand">Flight Booking</div>
        <ul className="navbar-menu">
          <li className="navbar-item"><a onClick={handleHome}>Create New Booking</a></li>
          <li className="navbar-item"><a onClick={handleLogout}>Logout</a></li>
        </ul>
      </nav>
      <main role="main">
        <Card className="bg-light mt-4">
          <Card.Body>
            {/* <Card.Title>Manage Your Bookings</Card.Title> */}
          </Card.Body>
        </Card>
        <Container className="view-booking-container mt-4">
          <Row>
            <Col>
              {error && <Alert variant="danger">{error}</Alert>}
              {successMessage && <Alert variant="success">{successMessage}</Alert>} {/* Display success message */}
              <Table striped bordered hover responsive>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Source</th>
                    <th>Destination</th>
                    <th>Date</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {searchQueries.length > 0 ? (
                    searchQueries.map(query => (
                      <tr key={query.id} className='table-rowclass'>
                        <td>{query.id}</td>
                        <td>{query.source}</td>
                        <td>{query.destination}</td>
                        <td>{query.date}</td>
                        <td>
                          <Button
                            variant="warning"
                            className="me-2"
                            onClick={() => handleUpdate(query.id)}
                          >
                            Update
                          </Button>
                          <Button
                            variant="danger"
                            className="me-2"
                            onClick={() => handleCancel(query.id)}
                          >
                            Cancel
                          </Button>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="5" className="text-center">No data available</td>
                    </tr>
                  )}
                </tbody>
              </Table>
            </Col>
          </Row>
        </Container>
      </main>
    </>
  );
}

export default ViewBooking;
