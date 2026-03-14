import React from "react";
import { Link } from "react-router-dom";

const UserDashboard = ({ currentUser }) => {
  if (!currentUser) {
    return <p>Please log in to view your dashboard</p>; 
  }

  return (
   <main>
      <h1>Welcome, {currentUser.username}!</h1>
      <p>What would you like to explore today?</p>

      <div className="dashboard-links">

        <Link to="/recipes" className="dashboard-card">
          🍹 Browse All Recipes
        </Link>

        <Link to="/favorites" className="dashboard-card">
          ⭐ My Favorite Recipes
        </Link>

        <Link to="/reviews" className="dashboard-card">
          📝 My Reviews & Ratings
        </Link>

      </div>
    </main>
  );
};

export default UserDashboard;