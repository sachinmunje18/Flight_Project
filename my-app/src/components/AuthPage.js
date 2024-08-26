import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './AuthPage.css';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function AuthPage() {
  const [isLogin, setIsLogin] = useState(true);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const handleNameChange = (e) => {
    const { value } = e.target;
    // Allow only alphabetic characters and spaces
    if (/^[A-Za-z\s]*$/.test(value)) {
      setName(value);
    } else {
      toast.error('Name should contain only alphabetic characters and spaces.');
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      toast.error('Password and Confirm Password should be the same');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/flight/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email, password }),
      });

      if (response.ok) {
        toast.success('User registered successfully!');
        setName('');
        setEmail('');
        setPassword('');
        setConfirmPassword('');
        setIsLogin(true);
      } else {
        const errorText = await response.text();
        toast.error('Registration failed: ' + errorText);
      }
    } catch (error) {
      console.error('Error:', error);
      toast.error('Something went wrong');
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/flight/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      if (response.ok) {
        toast.success('Login successful!');
        setTimeout(() => {
          navigate('/dashboard');
        }, 1000);  // Added delay to ensure toast is shown before navigation
      } else {
        toast.error('Invalid credentials');
      }
    } catch (error) {
      console.error('Error:', error);
      toast.error('Something went wrong');
    }
  };

 

  return (
    <>
      <ToastContainer />
      <div className="auth-container">
        <div className="auth-box">
          <div className="welcome-message">
            <h3>Welcome to FlightBooker!</h3>
            <p>Start your journey today!</p>
          </div>
          <div className="wrapper">
            {isLogin ? (
              <div className="login-form">
                <h2>Login</h2>
                <form onSubmit={handleLogin}>
                  <div className="input-field">
                    <label htmlFor="email">Enter your email</label>
                    <input
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  <div className="input-field">
                    <label htmlFor="password">Enter your password</label>
                    <input
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <button type="submit" className="btn-login">
                      Log In
                    </button>
                  </div>
                  <div className="register">
                    <p>
                      Don't have an account?{' '}
                      <button
                        type="button"
                        style={{ padding: '5px 30px' }}
                        onClick={() => setIsLogin(false)}
                      >
                        Register
                      </button>
                    </p>
                  </div>
                </form>
              </div>
            ) : (
              <div className="registration-form">
                <h2>Register</h2>
                <form onSubmit={handleRegister}>
                  <div className="input-field">
                    <label htmlFor="name">Enter your name</label>
                    <input
                      type="text"
                      value={name}
                      onChange={handleNameChange}
                      required
                    />
                  </div>
                  <div className="input-field">
                    <label htmlFor="email">Enter your email</label>
                    <input
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  <div className="input-field">
                    <label htmlFor="password">Enter your password</label>
                    <input
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </div>
                  <div className="input-field">
                    <label htmlFor="confirm-password">Confirm password</label>
                    <input
                      type="password"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      required
                    />
                  </div>
                  <button type="submit" className="btn-register">
                    Register
                  </button>
                  <div className="register">
                    <p>
                      Already have an account?{' '}
                      <button
                        type="button"
                        style={{ padding: '5px 30px' }}
                        onClick={() => setIsLogin(true)}
                      >
                        Login
                      </button>
                    </p>
                  </div>
                </form>
              </div>
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default AuthPage;