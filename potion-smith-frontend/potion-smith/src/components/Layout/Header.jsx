import NavBar from "./NavBar";
import { useState } from "react";
import { Link, useLocation } from "react-router-dom";


const Header = ({ searchQuery, setSearchQuery, isLoggedIn, setIsLoggedIn, currentUser, setCurrentUser }) => {
  const location = useLocation();

// Show the dashboard link only if the user is logged in and not already on the dashboard page
  const showDashboardLink = isLoggedIn && location.pathname !== "/dashboard";
  return (
    <header>
      <NavBar
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        isLoggedIn={isLoggedIn}
        setIsLoggedIn={setIsLoggedIn}
        currentUser={currentUser}
        setCurrentUser={setCurrentUser}
      />
      {/* Render the Dashboard link outside of NavBar */}
      {showDashboardLink && (
        <div style={{ padding: "0.5rem 1rem" }}>
          <Link to="/dashboard" style={{ textDecoration: "none", fontWeight: "bold" }}>
            🏠 Back to Dashboard
          </Link>
        </div>
      )}
    </header>
  );
};

export default Header;