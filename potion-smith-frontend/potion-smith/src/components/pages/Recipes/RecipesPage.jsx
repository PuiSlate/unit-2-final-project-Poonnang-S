import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import RecipeCard from "./RecipeCard";
import { recipeImages } from "../../../assets/images/images";
import Spacer from "../../common/spacer";

const RecipesPage = () => {
  const navigate = useNavigate();
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRecipes = async () => {
      setLoading(true);
      setError("");
      try {
        const response = await fetch("http://localhost:8080/api/drinks");
        if (!response.ok) {
          setError("Failed to load recipes");
          return;
        }
        const data = await response.json();
        setRecipes(data);
      } catch (err) {
        console.error("Error fetching recipes:", err);
        setError("Error fetching recipes");
      } finally {
        setLoading(false);
      }
    };

    fetchRecipes();
  }, []);

  if (loading) return <h2>Loading recipes...</h2>;
  if (error) return <h2>{error}</h2>;

  return (
    <main className="recipes-page">
      <h1>All Recipes</h1>
      <Spacer marginY="20px" />
      <div className="recipes-grid">
        {recipes.length > 0
          ? recipes.map((recipe) => (
              <RecipeCard
                key={recipe.id} // Unique key fixes the warning
                recipe={recipe}
                onClick={() => navigate(`/recipes/${recipe.id}`)} // Pass correct ID
              />
            ))
          : <p>No recipes found.</p>}
      </div>
    </main>
  );
};

export default RecipesPage;