import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import RecipeCard from "./RecipeCard";
import { recipeImages } from "../../../assets/images/images";
import Spacer from "../../common/spacer";

const RecipesPage = () => {
  const navigate = useNavigate();

  // -------------------
  // State
  // -------------------
  const [recipes, setRecipes] = useState([]);
  const [spiritOptions, setSpiritOptions] = useState([]);
  const [themeOptions, setThemeOptions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [selectedSpirit, setSelectedSpirit] = useState("");
  const [selectedTheme, setSelectedTheme] = useState("");

  // -------------------
  // Fetch recipes
  // -------------------
  const fetchRecipes = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await fetch("http://localhost:8080/api/drinks");
      if (!response.ok) throw new Error("Failed to load recipes");
      const data = await response.json();
      setRecipes(data);
    } catch (err) {
      console.error("Error fetching recipes:", err);
      setError("Error fetching recipes");
    } finally {
      setLoading(false);
    }
  };

  // -------------------
  // Fetch category options
  // -------------------
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

      // Extract only the titles for the dropdown
    const titles = data.map((theme) => theme.title);
    setThemeOptions(titles);
    
    } catch (err) {
      console.error(err);
    }
  };

  // -------------------
  // Initial data load
  // -------------------
  useEffect(() => {
    fetchRecipes();
    fetchSpiritOptions();
    fetchThemeOptions();
  }, []);

  // -------------------
  // Filtered recipes
  // -------------------
  const filteredRecipes = recipes.filter((r) => {
    const matchesSpirit = selectedSpirit ? r.spiritCategoryTitle === selectedSpirit : true;
    const matchesTheme = selectedTheme ? r.themeCategoryTitle === selectedTheme : true;
    return matchesSpirit && matchesTheme;
  });

  // -------------------
  // Render
  // -------------------
  if (loading) return <h2>Loading recipes...</h2>;
  if (error) return <h2>{error}</h2>;

  return (
    <main className="recipes-page">
      <h1>All Recipes</h1>
      <Spacer marginY="20px" />

      {/* ------------------- */}
      {/* Category Filters */}
      {/* ------------------- */}
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

      {/* ------------------- */}
      {/* Recipe Grid */}
      {/* ------------------- */}
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