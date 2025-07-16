import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../dashboard/Dashboard.css';

const BalanceSheet = () => {
  const [balanceData, setBalanceData] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/anjuman/v1/member')
      .then(response => {
        setBalanceData(response.data);
      })
      .catch(error => {
        console.error('Error fetching balance sheet data:', error);
      });
  }, []);

  // Add at the top inside the BalanceSheet function
const [showForm, setShowForm] = useState(false);
const [formMode, setFormMode] = useState("create"); // "create" or "update"
const [selectedId, setSelectedId] = useState(null); // for update/delete
const [formData, setFormData] = useState({

  name: '',
  paidAmount: '',
  pendingAmount: ''

  // ... other months
});

// Button handlers
const handleCreate = () => {
  setFormMode("create");
  setFormData({ Name: '' });
  setShowForm(true);
};

const handleUpdate = (id) => {
  const record = balanceData.find(item => item.id === id);
  if (!record) return;
  setFormMode("update");
  setFormData(record);
  setSelectedId(id);
  setShowForm(true);
};

const handleDelete = (id) => {
  if (!window.confirm("Are you sure you want to delete this record?")) return;
  axios.delete(`http://localhost:8080/anjuman/v1/balancesheet?balanceSheetId=${id}`)
    .then(() => {
      alert("Deleted successfully");
      setBalanceData(prev => prev.filter(item => item.id !== id));
    })
    .catch(err => alert("Error deleting: " + err.message));
};

  {/* const toggleMonths = () => setShowAllMonths(!showAllMonths); */}

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
        <h2 className="dashboard-title">📄 Member Details</h2>

       {/* <button onClick={toggleMonths} className="toggle-button">
          {showAllMonths ? 'Hide Monthly Breakdown' : 'Show Monthly Breakdown'}
        </button> */}

        <div className="action-bar">
            <button className="primary-btn" onClick={handleCreate}>Create Member</button>
        {/* <button className="primary-btn"a onClick={() => selectedId && handleUpdate(selectedId)} disabled={!selectedId}>Update Balance Sheet</button>
            <button className="danger-btn" onClick={() => selectedId && handleDelete(selectedId)} disabled={!selectedId}>Delete Balance Sheet</button>
        */}
        </div>

                {/* Overlay Form */}
        {showForm && (
        <div className="overlay">
            <div className="form-card">
            <h3>{formMode === "create" ? "Add New Balance Sheet" : "Update Balance Sheet"}</h3>

            <input
                type="text"
                placeholder="Member Name"
                value={formData.memberName}
                onChange={(e) => setFormData({ ...formData, memberName: e.target.value })}
            />
            <input
                type="number"
                placeholder="Year"
                value={formData.year}
                onChange={(e) => setFormData({ ...formData, year: e.target.value })}
            />
            {/* More month fields as needed */}
            
            <div className="form-buttons">
                <button
                className="primary-btn"
                onClick={() => {
                    const method = formMode === "create" ? axios.post : axios.put;
                    method("http://localhost:8080/anjuman/v1/member", formData)
                    .then(() => {
                        alert("Success!");
                        setShowForm(false);
                        window.location.reload();
                    })
                    .catch(err => alert("Error: " + err.message));
                }}
                >
                {formMode === "create" ? "Submit" : "Update"}
                </button>
                <button className="danger-btn" onClick={() => setShowForm(false)}>Cancel</button>
            </div>
            </div>
        </div>
        )}


        <div className="table-wrapper">
          <table className="balance-table">
            <thead>
              <tr>
                <th>#</th>
                <th>Member Name</th>
                <th>Paid</th>
                <th>Pending</th>
              </tr>
            </thead>
            <tbody>
              {balanceData.map((item, index) => (
                <tr key={item.id}>
                  <td>{index + 1}</td>
                  <td>{item.name}</td>
                  <td>₹{item.paidAmount}</td>
                  <td>₹{item.pendingAmount}</td>
                </tr>
              ))}
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

export default BalanceSheet;
