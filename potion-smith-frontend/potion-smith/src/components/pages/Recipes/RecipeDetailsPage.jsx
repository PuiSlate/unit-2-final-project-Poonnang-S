import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { recipeImages } from "../../../assets/images/images";

const RecipeDetailsPage = ({ currentUser }) => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [recipe, setRecipe] = useState(null);
  const [comments, setComments] = useState([]);
  const [reviewText, setReviewText] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  const isLoggedIn = !!currentUser;

  // -------------------
  // Fetch comments
  // -------------------
  const fetchComments = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/drinks/${id}/comments`);
      if (!response.ok) throw new Error("Failed to load comments");
      const data = await response.json();
      setComments(data);
    } catch (err) {
      console.error(err);
    }
  };

  // -------------------
  // Fetch recipe details
  // -------------------
  const fetchRecipe = async () => {
    if (!id) {
      setError("Invalid recipe id");
      setLoading(false);
      return;
    }

    setLoading(true);
    setError("");

    try {
      const response = await fetch(`http://localhost:8080/api/drinks/details/${id}`);
      if (response.status === 404) setError("Recipe not found");
      else if (!response.ok) setError("Failed to load recipe");
      else {
        const data = await response.json();
        setRecipe(data);
      }
    } catch (err) {
      console.error(err);
      setError("Error fetching recipe");
    } finally {
      setLoading(false);
    }
  };

  // -------------------
  // Run on mount
  // -------------------
  useEffect(() => {
    fetchRecipe();
    fetchComments();
  }, [id]);

  if (loading) return <h2>Loading recipe...</h2>;
  if (error)
    return (
      <div>
        <h2>{error}</h2>
        <button onClick={() => navigate("/recipes")}>← Back to All Recipes</button>
      </div>
    );

  // -------------------
  // Recipe details helpers
  // -------------------
  const maxRating = 5;
  const instructionSteps = recipe?.drinkInstructions
    ? recipe.drinkInstructions.split(/\.\s+/).filter((s) => s.trim().length > 0)
    : [];
  const ingredientList = recipe?.drinkIngredients
    ? recipe.drinkIngredients.split(";").filter((s) => s.trim().length > 0)
    : [];

  // -------------------
  // Rating handler
  // -------------------
  const handleRating = async (rating) => {
    if (!currentUser) {
      navigate("/login");
      return;
    }

    setRecipe({ ...recipe, userRating: rating });

    try {
      const response = await fetch("http://localhost:8080/api/ratings/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          drinkId: recipe.id,
          userId: currentUser.id,
          rating,
        }),
      });
      if (!response.ok) throw new Error("Failed to save rating");
    } catch (err) {
      console.error(err);
      alert("Error saving rating");
    }
  };

  // -------------------
  // Favorite handler
  // -------------------
  const toggleFavorite = async () => {
    if (!currentUser) {
      navigate("/login");
      return;
    }

    try {
      await fetch("http://localhost:8080/api/favorites/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          drinkId: recipe.id,
          userId: currentUser.id,
        }),
      });
      setRecipe({ ...recipe, isFavorite: !recipe.isFavorite });
    } catch (err) {
      console.error(err);
      alert("Error saving favorite");
    }
  };

  // -------------------
  // Submit comment handler
  // -------------------
  const submitReview = async () => {
    if (!currentUser) {
      navigate("/login");
      return;
    }

    if (!reviewText.trim()) {
      alert("Review cannot be empty");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/drinks/${recipe.id}/comments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userId: currentUser.id,
          drinkId: recipe.id,
          commentText: reviewText,
        }),
      });

      if (!response.ok) throw new Error("Failed to submit review");

      const newComment = await response.json();
      setComments((prev) => [newComment, ...prev]); // prepend new comment
      setReviewText("");
    } catch (err) {
      console.error(err);
      alert("Error submitting review");
    }
  };

  return (
    <main className="recipe-details-page">
      <button onClick={() => navigate("/recipes")}>← Back to All Recipes</button>

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

          {!isLoggedIn && (
            <div className="login-prompt">
              <p>🔒 Log in to rate, save recipes, and leave reviews!</p>
            </div>
          )}

          {/* Rating */}
          <div className="recipe-rating">
            <h3>Rate this recipe:</h3>
            <div className="stars">
              {Array.from({ length: maxRating }, (_, i) => {
                const starNumber = i + 1;
                return (
                  <span
                    key={starNumber}
                    className={`star ${
                      starNumber <= (recipe.userRating || 0) ? "filled" : ""
                    } ${!isLoggedIn ? "disabled" : ""}`}
                    onClick={() => handleRating(starNumber)}
                  >
                    ★
                  </span>
                );
              })}
            </div>
            {recipe?.userRating > 0 && (
              <p>
                You rated this recipe {recipe.userRating} out of {maxRating} stars
              </p>
            )}
          </div>

          {/* Favorite */}
          <div className="favorite-section">
            <button
              className={recipe.isFavorite ? "favorited" : ""}
              disabled={!isLoggedIn}
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
            ingredientList.map((item, index) => <li key={index}>{item}</li>)
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
            instructionSteps.map((step, index) => <li key={index}>{step}.</li>)
          ) : (
            <li>No instructions listed</li>
          )}
        </ol>
      </section>

      {/* Review Form */}
      <section className="review-section">
        <h2>Leave a Review</h2>
        {!isLoggedIn ? (
          <p>Please log in to leave a review.</p>
        ) : (
          <>
            <textarea
              placeholder="Share your thoughts..."
              value={reviewText}
              onChange={(e) => setReviewText(e.target.value)}
              rows="4"
            />
            <button onClick={submitReview}>Submit Review</button>
          </>
        )}
      </section>

      {/* Comments */}
      <section className="comments-list">
        <h3>Reviews</h3>
        {comments.length === 0 ? (
          <p>No reviews yet.</p>
        ) : (
          comments.map((comment) => (
            <div key={comment.id} className="comment-card">
              <p>{comment.commentText}</p>
              <small>
                {comment.username} •{" "}
                {comment.createdAt
                  ? new Date(comment.createdAt).toLocaleDateString()
                  : ""}
              </small>
            </div>
          ))
        )}
      </section>
    </main>
  );
};

export default RecipeDetailsPage;