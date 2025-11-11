import logo from './logo.svg';
import './App.css';
import React, { useState } from "react";
import Login from "./components/Login";
import Hello from "./components/Hello";
function App() {
 const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("token"));
  return (
 <div className="App">
      {isLoggedIn ? (
        <Hello onLogout={() => setIsLoggedIn(false)} />
      ) : (
        <Login onLogin={() => setIsLoggedIn(true)} />
      )}
    </div>
  );
}

export default App;
