import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { recipeImages } from "../../../assets/images/images";

const RecipeDetailsPage = () => {
  const { id } = useParams();
  console.log("ID from URL:", id);  
  const navigate = useNavigate();
  const [recipe, setRecipe] = useState(null);
  const [error, setError] = useState(""); 
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchRecipe = async () => {
      if (!id) {
        setError("Invalid recipe id");
        setRecipe(null);
        setLoading(false);
        return;
      }
      setLoading(true);
      setError("");
      try {
        const response = await fetch(`http://localhost:8080/api/drinks/details/${id}`);

        if (response.status === 404) {
          setError("Recipe not found");
          setRecipe(null);
        } else if (!response.ok) {
          setError("Failed to load recipe");
          setRecipe(null);
        } else {
          const data = await response.json();
          setRecipe(data);
        }
      } catch (err) {
        console.error("Error fetching recipe:", err);
        setError("Error fetching recipe");
        setRecipe(null);
      } finally {
        setLoading(false);
      }
    };

    fetchRecipe();
  }, [id]);

  if (loading) {
    return (
      <main className="recipe-details-page">
        <h2>Loading recipe...</h2>
      </main>
    );
  }

  if (error) {
    return (
      <main className="recipe-details-page">
        <h2>{error}</h2>
        <button className="back-button" onClick={() => navigate("/recipes")}>
          ← Back to All Recipes
        </button>
      </main>
    );
  }

  const maxRating = 5;

  const handleRating = (rating) => {
    setRecipe({ ...recipe, userRating: rating });
  };

  const toggleFavorite = () => {
    setRecipe({ ...recipe, isFavorite: !recipe.isFavorite });
  };

  // Split instructions without adding extra periods
  const instructionSteps = recipe.drinkInstructions
    ? recipe.drinkInstructions
        .split(/\.\s+/)
        .map((step) => step.trim())
        .filter((step) => step.length > 0)
    : [];

  // Split ingredients
  const ingredientList = recipe.drinkIngredients
    ? recipe.drinkIngredients
        .split(";")
        .map((item) => item.trim())
        .filter((item) => item.length > 0)
    : [];

  return (
    <main className="recipe-details-page">
      <button className="back-button" onClick={() => navigate("/recipes")}>
        ← Back to All Recipes
      </button>

      <div className="recipe-details-header">
        <img
          className="recipe-details-image"
          src={recipeImages[recipe.imageId] || recipeImages["fallback.jpg"]}
          alt={recipe.drinkName}
        />
        <div>
          <h1>{recipe.drinkName}</h1>
          <h3>{recipe.spiritCategory}</h3>

          {/* Rating Section */}
          <div className="recipe-rating">
            <h3>Rate this recipe:</h3>
            <div className="stars">
              {Array.from({ length: maxRating }, (_, i) => {
                const starNumber = i + 1;
                return (
                  <span
                    key={`star-${starNumber}`}
                    className={`star ${
                      starNumber <= (recipe.userRating || 0) ? "filled" : ""
                    }`}
                    onClick={() => handleRating(starNumber)}
                  >
                    ★
                  </span>
                );
              })}
            </div>
            {recipe.userRating > 0 && (
              <p>
                You rated this recipe {recipe.userRating} out of {maxRating} stars
              </p>
            )}
          </div>

          {/* Favorite Section */}
          <div className="favorite-section">
            <button
              className={`favorite-btn ${recipe.isFavorite ? "favorited" : ""}`}
              onClick={toggleFavorite}
            >
              {recipe.isFavorite ? "❤️ Favorited" : "🤍 Save Recipe"}
            </button>
          </div>
        </div>
      </div>

      {/* Ingredients */}
      <section>
        <h2>Ingredients</h2>
        <ul>
          {ingredientList.length > 0
            ? ingredientList.map((item, index) => (
                <li key={`${item}-${index}`}>{item}</li>
              ))
            : <li>No ingredients listed</li>}
        </ul>
      </section>

      {/* Instructions */}
      <section>
        <h2>Instructions</h2>
        <ol>
          {instructionSteps.length > 0
            ? instructionSteps.map((step, index) => (
                <li key={`${step}-${index}`}>{step}.</li>
              ))
            : <li>No instructions listed</li>}
        </ol>
      </section>
    </main>
  );
  
};

export default RecipeDetailsPage;