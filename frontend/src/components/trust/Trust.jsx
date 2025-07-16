import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../dashboard/Dashboard.css';

const TrustDetail = () => {


  return (
    <div className="main-wrapper">

      {/* Header */}
      <div className="header-title">
        <h1>Anjuman Jamat-ul-Muslimin, Sayyed Mohalla</h1>
      </div>

      {/* Navbar */}
      <nav className="navbar">
        <button className="menu-toggle">☰ Menu</button>
        <ul className="nav-links active">
          <li><a href="dashboard">Dashboard</a></li>
          <li><a href="/balancesheet">Balance Sheet</a></li>
          <li><a href="/member">Member</a></li>
          <li><a href="/trust">Trust</a></li>
          <li><a href="noticeBoard">Notice Board</a></li>
          <li><a href="#">Admin</a></li>
        </ul>
      </nav>

      {/* Table Section */}
      <div className="dashboard-container">
        <h2 className="dashboard-title">📄 Our Trust</h2>
        <div className="table-wrapper">
          <table className="balance-table">
            <thead>
              <tr>
                <th>Trust Members</th>
                <th>Designation</th>
                <th>contact number</th>
              </tr>
            </thead>
            <tbody>
                <tr>
                  <td>Trust Member1</td>
                  <td>President</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member2</td>
                  <td>Vice President</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member3</td>
                  <td>Treasurer</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member4</td>
                  <td>Senior Consultant</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member5</td>
                  <td>Consultant</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member6</td>
                  <td>Trustee</td>
                  <td>987654321</td>
                </tr>
                <tr>
                  <td>Trust Member7</td>
                  <td>Trustee</td>
                  <td>987654321</td>
                </tr>
                    <tr>
                  <td>Trust Member8</td>
                  <td>Trustee</td>
                  <td>987654321</td>
                </tr>
                    <tr>
                  <td>Trust Member9</td>
                  <td>Trustee</td>
                  <td>987654321</td>
                </tr>
                    <tr>
                  <td>Trust Member10</td>
                  <td>Trustee</td>
                  <td>987654321</td>
                </tr>
            </tbody>
          </table>
        </div>
      </div>

      {/* Footer */}
      <footer className="footer">
        © 2025 Anjuman Jamat-ul-Muslimin, Sayyed Mohalla. All rights reserved. | Developed by Moazzam Sayyed
      </footer>
    </div>
  );
};

export default TrustDetail;
