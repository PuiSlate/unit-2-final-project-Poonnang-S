import { Link, useNavigate, useLocation } from "react-router-dom";
import { useState } from "react";

const NavBar = ({ isLoggedIn, setIsLoggedIn, currentUser, setCurrentUser }) => {
  const navigate = useNavigate();
 const [searchText, setSearchText] = useState("");


// Simplified NavBar search to search recipe nams with simple logic
  const handleSearch = (e) => {
    e.preventDefault();

    if (searchText.trim() === "") {
      navigate("/recipes");
    } else {
      navigate(`/recipes?search=${searchText.trim()}`);
    }
  };



  return (
    <div className="navbar">
      <div className="logo">
        <Link to="/">Potion Smith</Link>
      </div>

      <ul className="navbar-links">
        <li>
          <Link to="/recipes">Recipes</Link>
        </li>
        <li>
          <Link to="/about">About</Link>
        </li>
        <li>
          <Link to="/contact">Contact</Link>
        </li>
        <li>
          <Link to="/subscribe">Subscribe</Link>
        </li>

        {/* LOGIN/LOGOUT */}
        <li>
          {isLoggedIn ? (
            <>
              <span>Welcome, {currentUser?.username}</span>
              <button
                onClick={() => {
                  setIsLoggedIn(false);
                  setCurrentUser(null);
                  navigate("/");
                }}
              >
                Log Out
              </button>
            </>
          ) : (
            <Link to="/login">Log In</Link>
          )}
        </li>
      </ul>

      <form className="navbar-search" onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="Search recipe name…"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          className="search-input"
        />
        <button type="submit" className="search-button" aria-label="Search">
          🔍
        </button>
      </form>
    </div>
  );
};

export default NavBar;
