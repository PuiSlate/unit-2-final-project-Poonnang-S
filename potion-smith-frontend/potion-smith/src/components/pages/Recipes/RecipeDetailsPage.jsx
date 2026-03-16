import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { recipeImages } from "../../../assets/images/images";

const RecipeDetailsPage = () => {
  const { id } = useParams(); // id from URL
  const navigate = useNavigate();
  const [recipe, setRecipe] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  const isLoggedIn = !!localStorage.getItem("Token"); // Login state check
  const [reviewText, setReviewText] = useState(""); // State for review input

  useEffect(() => {
    const fetchRecipe = async () => {
      if (!id) {
        setError("Invalid recipe id");
        setLoading(false);
        return;
      }

      setLoading(true);
      setError("");

      try {
        const response = await fetch(
          `http://localhost:8080/api/drinks/details/${id}`,
        );

        if (response.status === 404) {
          setError("Recipe not found");
        } else if (!response.ok) {
          setError("Failed to load recipe");
        } else {
          const data = await response.json();
          setRecipe(data);
        }
      } catch (err) {
        console.error("Error fetching recipe:", err);
        setError("Error fetching recipe");
      } finally {
        setLoading(false);
      }
    };

    fetchRecipe();
  }, [id]);

  if (loading) return <h2>Loading recipe...</h2>;
  if (error)
    return (
      <div>
        <h2>{error}</h2>
        <button onClick={() => navigate("/recipes")}>
          ← Back to All Recipes
        </button>
      </div>
    );

  const maxRating = 5;

  const handleRating = (rating) => {
    setRecipe({ ...recipe, userRating: rating });
  };

  const toggleFavorite = () => {
    setRecipe({ ...recipe, isFavorite: !recipe.isFavorite });
  };

  const instructionSteps = recipe.drinkInstructions
    ? recipe.drinkInstructions.split(/\.\s+/).filter((s) => s.trim().length > 0)
    : [];

  const ingredientList = recipe.drinkIngredients
    ? recipe.drinkIngredients.split(";").filter((s) => s.trim().length > 0)
    : [];

  return (
    <main className="recipe-details-page">
      <button onClick={() => navigate("/recipes")}>
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
          <h3>{recipe.spiritCategoryTitle || "No Spirit Category"}</h3>
          <h3>{recipe.themeCategoryTitle || "No Theme Category"}</h3>

          {/* Login Prompt banner */}
          {!isLoggedIn && (
            <div className="login-prompt">
              <p>🔒 Log in to rate, save recipes, and leave reviews!</p>
            </div>
          )}

          {/* Rating Section */}
          <div className="recipe-rating">
            <h3>Rate this recipe:</h3>
            <div className="stars">
              {Array.from({ length: maxRating }, (_, i) => {
                const starNumber = i + 1;
                return (
                  <span
                    key={starNumber}
                    className={`star ${starNumber <= (recipe.userRating || 0) ? "filled" : ""} ${!isLoggedIn ? "disabled" : ""}`} // Disable if not logged in
                    onClick={() => handleRating(starNumber)}
                  >
                    ★
                  </span>
                );
              })}
            </div>
            {recipe.userRating > 0 && (
              <p>
                You rated this recipe {recipe.userRating} out of {maxRating}{" "}
                stars
              </p>
            )}
          </div>

          {/* Favorite Section */}
          <div className="favorite-section">
            <button
              className={recipe.isFavorite ? "favorited" : ""}
              disabled={!isLoggedIn} // Disable if not logged in
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
          {ingredientList.length > 0 ? (
            ingredientList.map((item, index) => (
              <li key={`ing-${index}`}>{item}</li>
            ))
          ) : (
            <li>No ingredients listed</li>
          )}
        </ul>
      </section>

      {/* Instructions */}
      <section>
        <h2>Instructions</h2>
        <ol>
          {instructionSteps.length > 0 ? (
            instructionSteps.map((step, index) => (
              <li key={`step-${index}`}>{step}.</li>
            ))
          ) : (
            <li>No instructions listed</li>
          )}
        </ol>
      </section>

      {/* Review Section */}
      <section className="review-section">
        <h2>Leave a Review</h2>

        {!isLoggedIn ? (
          <p>Please log in to leave a review.</p>
        ) : (
          <>
            <textarea
              placeholder="Share your thoughts about this recipe..."
              value={reviewText}
              onChange={(e) => setReviewText(e.target.value)}
              rows="4"
            />

            <button onClick={submitReview}>Submit Review</button>
          </>
        )}
      </section>
    </main>
  );
};

export default RecipeDetailsPage;
