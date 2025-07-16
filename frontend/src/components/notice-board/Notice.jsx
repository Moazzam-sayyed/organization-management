import React from 'react';
import '../dashboard/Dashboard.css';

const NoticeBoard = () => {
  const notices = [
    {
      title: 'Monthly Jamaat Meeting Summary',
      date: '2025-07-01',
      points: [
        'Meeting held at community hall with 40+ members.',
        'Approved monthly budget for education support.',
        'Scheduled next trust audit by end of July.'
      ]
    },
    {
      title: 'Ramzan Charity Collection',
      date: '2025-06-10',
      points: [
        'Total ₹25,000 collected from members.',
        'Distribution planned for 5 families.',
        'Remaining funds will go to education support.'
      ]
    },
    {
      title: 'New Member Registration Update',
      date: '2025-05-20',
      points: [
        '7 new members registered last month.',
        'Verification completed for 5 applications.',
        'All new members will be added to monthly reports.'
      ]
    }
  ];

  return (
    <div className="main-wrapper">
      {/* Title */}
      <div className="header-title">
        <h1>Anjuman Jamat-ul-Muslimin, Sayyed Mohalla</h1>
      </div>

      {/* Navbar */}
      <nav className="navbar">
        <ul className="nav-links active">
          <li><a href="dashboard">Dashboard</a></li>
          <li><a href="/balancesheet">Balance Sheet</a></li>
          <li><a href="/member">Member</a></li>
          <li><a href="/trust">Trust</a></li>
          <li><a href="noticeBoard">Notice Board</a></li>
          <li><a href="#">Admin</a></li>
        </ul>
      </nav>

      {/* Notice Board Section */}
      <div className="dashboard-container">
        <h2 className="dashboard-title">📌 Notice Board</h2>
        <div className="notice-container">
          {notices.map((notice, index) => (
            <div className="notice-card" key={index}>
              <h3 className="notice-title">{notice.title}</h3>
              <p className="notice-date">📅 {notice.date}</p>
              <ul className="notice-points">
                {notice.points.map((pt, i) => (
                  <li key={i}>✅ {pt}</li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      </div>

      {/* Footer */}
      <footer className="footer">
        © 2025 Anjuman Jamat-ul-Muslimin, Sayyed Mohalla. All rights reserved. | Developed by Moazzam Sayyed
      </footer>
    </div>
  );
};

export default NoticeBoard;
