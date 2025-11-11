import React, { useEffect, useState } from "react";
import axios from "axios";
import { getAuthHeader, logout } from "../services/authService";
import "./Hello.css"; // importer le CSS

const Hello = ({ onLogout }) => {
  const [message, setMessage] = useState("");

  useEffect(() => {
    const fetchHello = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/hello", {
          headers: getAuthHeader(),
        });
        setMessage(response.data.message);
      } catch (err) {
        setMessage("Erreur d'authentification");
      }
    };
    fetchHello();
  }, []);

  return (
    <div className="hello-container">
      <div className="hello-card">
        <h2 className="hello-title">Page protégée</h2>
        <p className="hello-message">{message}</p>
        <button
          className="hello-button"
          onClick={() => {
            logout();
            onLogout();
          }}
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Hello;
