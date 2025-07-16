import logo from './logo.svg';
import './App.css';
import Dashboard from './components/dashboard/Dashboard.jsx'
import BalanceSheet from './components/balance-sheet/BalanceSheet.js'
import Member from './components/member/Member.jsx'
import Trust from './components/trust/Trust.jsx'
import Notice from './components/notice-board/Notice.jsx'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path='/balancesheet' element={<BalanceSheet/>} />
        <Route path='/member' element={<Member/>} />
        <Route path='/trust' element={<Trust/>} />
        <Route path='/noticeBoard' element={<Notice/>} />
      </Routes>
    </Router>
  );
}

export default App;
