import { Link, useNavigate, useLocation } from "react-router-dom";

const NavBar = () => {
  const navigate = useNavigate();
  const location = useLocation();

   // Read search text directly from URL
  const params = new URLSearchParams(location.search);
  const searchQuery = params.get("search") || "";

  const handleSearch = (e) => {
    e.preventDefault();

    // If search is empty, remove the param instead of leaving "?search="
    if (searchQuery.trim() === "") {
      params.delete("search");
    } else {
      params.set("search", searchQuery.trim());
    }

    navigate(`/recipes?${params.toString()}`);
  };

  const handleChange = (e) => {
    const value = e.target.value;
    const newParams = new URLSearchParams(location.search);

    if (value === "") newParams.delete("search");
    else newParams.set("search", value);

    // Update the URL as the user types
    navigate(`/recipes?${newParams.toString()}`, { replace: true });
  };

  return (
    <div className="navbar">
      <div className="logo">
        <Link to="/">Potion Smith</Link>
      </div>

      <ul className="navbar-links">
        <li><Link to="/recipes">Recipes</Link></li>
        <li><Link to="/about">About</Link></li>
        <li><Link to="/contact">Contact</Link></li>
        <li><Link to="/subscribe">Subscribe</Link></li>
      </ul>

      <form className="navbar-search" onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="Search recipes…"
          value={searchQuery}
          onChange={handleChange}
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
