import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Dashboard.css';

const Dashboard = () => {
  const [data, setData] = useState({
    totalAvailableAmount: 0,
    totalReceivedAmount: 0,
    totalPendingAmount: 0,
    totalExpendAmount: 0,
  });

  const [menuOpen, setMenuOpen] = useState(false);
  
  useEffect(() => {
    axios.get('http://localhost:8080/anjuman/v1/dashboard?dashboardId=1') // Replace with your API endpoint
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error('Error fetching dashboard data:', error);
      });
  }, []);

    return (
    <div className="main-wrapper">
      
      {/* Title Banner */}
      <div className="header-title">
        <h1>Anjuman Jamat-ul-Muslimin, Sayyed Mohalla</h1>
      </div>

      {/* Navbar with toggle */}
      <nav className="navbar">
        <button className="menu-toggle" onClick={() => setMenuOpen(!menuOpen)}>
          ☰ Menu
        </button>
        <ul className={`nav-links ${menuOpen ? 'active' : ''}`}>
          <li><a href="dashboard">Dashboard</a></li>
          <li><a href="/balancesheet">Balance Sheet</a></li>
          <li><a href="/member">Member</a></li>
          <li><a href="/trust">Trust</a></li>
          <li><a href="noticeBoard">Notice Board</a></li>
          <li><a href="#">Admin</a></li>
        </ul>
      </nav>

      {/* Dashboard Section */}
      <div className="dashboard-container">
        <h2 className="dashboard-title">💼 Financial Overview</h2>
        <div className="tile-container">
          <div className="tile available">
            <h3>Total Available</h3>
            <p>₹{data.totalAvailableAmount}</p>
          </div>
          <div className="tile received">
            <h3>Total Received</h3>
            <p>₹{data.totalReceivedAmount}</p>
          </div>
          <div className="tile pending">
            <h3>Total Pending</h3>
            <p>₹{data.totalPendingAmount}</p>
          </div>
          <div className="tile expended">
            <h3>Total Expended</h3>
            <p>₹{data.totalExpendAmount}</p>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="footer">
        © 2025 Anjuman Jamat-ul-Muslimin, Sayyed Mohalla. All rights reserved. | Developed by Moazzam Sayyed
      </footer>
    </div>
  );
};

export default Dashboard;