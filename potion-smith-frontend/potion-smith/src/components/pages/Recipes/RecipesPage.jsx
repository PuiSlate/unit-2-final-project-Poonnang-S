import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import RecipeCard from "./RecipeCard";
import Spacer from "../../common/spacer";

const RecipesPage = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const params = new URLSearchParams(location.search);
  const search = params.get("search") || "";

  const [recipes, setRecipes] = useState([]);
  const [spiritOptions, setSpiritOptions] = useState([]);
  const [themeOptions, setThemeOptions] = useState([]);
  const [selectedSpirit, setSelectedSpirit] = useState("");
  const [selectedTheme, setSelectedTheme] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Fetch recipes
  useEffect(() => {
    const fetchRecipes = async () => {
      setLoading(true);
      setError("");
      try {
        const res = await fetch("http://localhost:8080/api/drinks");
        if (!res.ok) throw new Error("Failed to load recipes");
        const data = await res.json();
        setRecipes(data);
      } catch (err) {
        console.error(err);
        setError("Error fetching recipes");
      } finally {
        setLoading(false);
      }
    };

    fetchRecipes();
  }, []);

  // Fetch dropdown options
  useEffect(() => {
    const fetchSpiritOptions = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/spirit-categories");
        if (!res.ok) throw new Error("Failed to load spirit categories");
        const data = await res.json();
        setSpiritOptions(data);
      } catch (err) {
        console.error(err);
      }
    };

    const fetchThemeOptions = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/theme-categories");
        if (!res.ok) throw new Error("Failed to load theme categories");
        const data = await res.json();
        const titles = data.map((theme) => theme.title);
        setThemeOptions(titles);
      } catch (err) {
        console.error(err);
      }
    };

    fetchSpiritOptions();
    fetchThemeOptions();
  }, []);

  // -------------------
  // Filter recipes by dropdowns + search
  // -------------------
  const filteredRecipes = recipes.filter((r) => {
    const matchesSpirit = selectedSpirit
      ? r.spiritCategoryTitle === selectedSpirit
      : true;
    const matchesTheme = selectedTheme
      ? r.themeCategoryTitle === selectedTheme
      : true;
    const matchesSearch = search
      ? r.drinkName?.toLowerCase().includes(search.toLowerCase())
      : true;

    return matchesSpirit && matchesTheme && matchesSearch;
  });

  if (loading) return <h2>Loading recipes...</h2>;
  if (error) return <h2>{error}</h2>;

  return (
    <main className="recipes-page">
      <h1>All Recipes</h1>
      <Spacer marginY="20px" />

      {/* Dropdown filters */}
      <div className="filters">
        <select
          value={selectedSpirit}
          onChange={(e) => setSelectedSpirit(e.target.value)}
        >
          <option value="">All Spirits</option>
          {spiritOptions.map((title) => (
            <option key={title} value={title}>
              {title}
            </option>
          ))}
        </select>

        <select
          value={selectedTheme}
          onChange={(e) => setSelectedTheme(e.target.value)}
        >
          <option value="">All Themes</option>
          {themeOptions.map((title) => (
            <option key={title} value={title}>
              {title}
            </option>
          ))}
        </select>
      </div>

      <Spacer marginY="20px" />

      {/* Recipe grid */}
      <div className="recipes-grid">
        {filteredRecipes.length > 0 ? (
          filteredRecipes.map((recipe) => (
            <RecipeCard
              key={recipe.id}
              recipe={recipe}
              onClick={() => navigate(`/recipes/${recipe.id}`)}
            />
          ))
        ) : (
          <p>No recipes found.</p>
        )}
      </div>
    </main>
  );
};

export default RecipesPage;