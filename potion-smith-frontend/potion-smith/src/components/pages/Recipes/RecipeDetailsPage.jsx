import { useParams, useNavigate } from "react-router-dom";
import { recipeImages } from "../../../assets/images/images";

const RecipeDetailsPage = ({ recipes, setRecipes }) => {
  const { id } = useParams();
  const navigate = useNavigate();

  // Find recipe from global state
  const recipe = recipes.find((r) => r.id === parseInt(id));

  if (!recipe) {
    return (
      <main className="recipe-details-page">
        <h2>Recipe not found</h2>
        <button className="back-button" onClick={() => navigate("/recipes")}>
          Back to All Recipes
        </button>
      </main>
    );
  }

  const maxRating = 5;

  // Update rating in global state
  const handleRating = (rating) => {
    const updatedRecipes = recipes.map((r) =>
      r.id === recipe.id ? { ...r, rating } : r
    );
    setRecipes(updatedRecipes);
  };

  // Toggle favorite in global state
  const toggleFavorite = () => {
    const updatedRecipes = recipes.map((r) =>
      r.id === recipe.id ? { ...r, isFavorite: !r.isFavorite } : r
    );
    setRecipes(updatedRecipes);
  };

  return (
    <main className="recipe-details-page">
      <button className="back-button" onClick={() => navigate("/recipes")}>
        ← Back to All Recipes
      </button>

      <div className="recipe-details-header">
        <img
          className="recipe-details-image"
          src={recipe.imageID ? recipeImages[recipe.imageID] : recipeImages["fallback.jpg"]}
          alt={recipe.name}
        />
        <div>
          <h1>{recipe.name}</h1>
          <h3>{recipe.category}</h3>

          {/* Rating Section */}
          <div className="recipe-rating">
            <h3>Rate this recipe:</h3>
            <div className="stars">
              {Array.from({ length: maxRating }, (_, i) => {
                const starNumber = i + 1;
                return (
                  <span
                    key={starNumber}
                    className={`star ${starNumber <= (recipe.rating || 0) ? "filled" : ""}`}
                    onClick={() => handleRating(starNumber)}
                  >
                    ★
                  </span>
                );
              })}
            </div>
            {recipe.rating > 0 && (
              <p>You rated this recipe {recipe.rating} out of {maxRating} stars</p>
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
          {recipe.ingredients?.map((item, index) => (
            <li key={index}>{item}</li>
          ))}
        </ul>
      </section>

      {/* Instructions */}
      <section>
        <h2>Instructions</h2>
        <ol>
          {recipe.instructions?.map((step, index) => (
            <li key={index}>{step}</li>
          ))}
        </ol>
      </section>
    </main>
  );
};

export default RecipeDetailsPage;
