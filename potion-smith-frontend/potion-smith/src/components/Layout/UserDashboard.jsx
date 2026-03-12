import React from "react";

const Dashboard = ({ user }) => {
  if (!user) {
    return <p>Please log in to see your dashboard.</p>;
  }

  return (
    <main className="dashboard-page">
      <h1>Welcome, {user.username}!</h1>
      <ul>
        <li><strong>User ID:</strong> {user.id}</li>
        <li><strong>Email:</strong> {user.email}</li>
        <li><strong>Age:</strong> {user.age}</li>
      </ul>
      <p>You can add favorite drinks, see your ratings, or explore new recipes from here!</p>
    </main>
  );
};

export default Dashboard;