import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { recipeImages } from "../../../assets/images/images";

const RecipeDetailsPage = ({ currentUser }) => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [recipe, setRecipe] = useState(null);
  const [comments, setComments] = useState([]);
  const [reviewText, setReviewText] = useState("");
  const [ratings, setRatings] = useState([]);
  const [userRating, setUserRating] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const isLoggedIn = !!currentUser;
  const maxRating = 5;

  // -------------------
  // Fetch recipe details
  // -------------------
  const fetchRecipe = async () => {
    if (!id) return;

    setLoading(true);
    setError("");

    try {
      const res = await fetch(`http://localhost:8080/api/drinks/details/${id}`);
      if (res.status === 404) setError("Recipe not found");
      else if (!res.ok) setError("Failed to load recipe");
      else {
        const data = await res.json();

        // Determine current user's rating
        if (currentUser && data.ratings && data.ratings.length > 0) {
          const existingRating = data.ratings.find(
            (r) => r.userId === currentUser.id,
          );
          data.userRating = existingRating ? existingRating.stars : 0;
          setUserRating(data.userRating);
        }

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
  // Fetch comments
  // -------------------
  const fetchComments = async () => {
    try {
      const res = await fetch(
        `http://localhost:8080/api/drinks/${id}/comments`,
      );
      if (!res.ok) throw new Error("Failed to load comments");
      const data = await res.json();
      setComments(data);
    } catch (err) {
      console.error(err);
    }
  };

  // -------------------
  // Fetch ratings
  // -------------------
  const fetchRatings = async () => {
    // if (!isLoggedIn) return;

    try {
      const res = await fetch(`http://localhost:8080/api/drinks/${id}/ratings`);
      if (!res.ok) throw new Error("Failed to load ratings");
      const data = await res.json();

      setRatings(data);

      // current user's rating
      const existing = currentUser
        ? data.find((r) => r.userId === currentUser.id)
        : null;
      setUserRating(existing ? existing.stars : 0);

      // average rating for display
      if (data.length > 0) {
        const avg = data.reduce((sum, r) => sum + r.stars, 0) / data.length;
        setRecipe((prev) => ({ ...prev, averageRating: avg }));
      }
    } catch (err) {
      console.error(err);
    }
  };

  // -------------------
  // Initial load
  // -------------------
  useEffect(() => {
    console.log("Current User:", currentUser);
    fetchRecipe();
    fetchComments();
    fetchRatings();
  }, [id, currentUser]);

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

  // -------------------
  // Recipe helpers
  // -------------------
  const ingredientList = recipe?.drinkIngredients
    ? recipe.drinkIngredients.split(";").filter((s) => s.trim().length > 0)
    : [];
  const instructionSteps = recipe?.drinkInstructions
    ? recipe.drinkInstructions.split(/\.\s+/).filter((s) => s.trim().length > 0)
    : [];

  // -------------------
  // Handle rating
  // -------------------
  const handleRating = async (rating) => {
    if (!isLoggedIn) return navigate("/login");

    setUserRating(rating);

    try {
      const res = await fetch(
        `http://localhost:8080/api/drinks/${id}/ratings/add`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            userId: currentUser.id,
            drinkId: recipe.id,
            stars: rating,
          }),
        },
      );

      if (!res.ok) throw new Error("Failed to save rating");
      await fetchRatings(); // refresh average and user ratings
    } catch (err) {
      console.error(err);
      alert("Error saving rating");
    }
  };

  // -------------------
  // Toggle favorite
  // -------------------
  const toggleFavorite = async () => {
    if (!isLoggedIn) return navigate("/login");

    try {
      await fetch(`http://localhost:8080/api/favorites/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId: currentUser.id, drinkId: recipe.id }),
      });
      setRecipe((prev) => ({ ...prev, isFavorite: !prev.isFavorite }));
    } catch (err) {
      console.error(err);
      alert("Error saving favorite");
    }
  };

  // -------------------
  // Submit comment
  // -------------------
  const submitReview = async () => {
    if (!isLoggedIn) return navigate("/login");
    if (!reviewText.trim()) return alert("Review cannot be empty");

    try {
      const res = await fetch(
        `http://localhost:8080/api/drinks/${id}/comments`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            userId: currentUser.id,
            drinkId: recipe.id,
            commentText: reviewText,
          }),
        },
      );

      if (!res.ok) throw new Error("Failed to submit review");

      const newComment = await res.json();
      setComments((prev) => [newComment, ...prev]);
      setReviewText("");
    } catch (err) {
      console.error(err);
      alert("Error submitting review");
    }
  };

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
      </div>
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
          {/* Average rating display */}
          {recipe.averageRating ? (
            <p>
              Average rating: {recipe.averageRating.toFixed(1)} / {maxRating} ⭐
            </p>
          ) : (
            <p>No ratings yet</p>
          )}

          {/* Stars for current user */}
          <div className="stars">
            {Array.from({ length: maxRating }, (_, i) => {
              const starNumber = i + 1;
              const isFilled = starNumber <= (userRating || 0);
              return (
                <span
                  key={starNumber}
                  className={`star ${isFilled ? "filled" : ""} ${!isLoggedIn ? "disabled" : ""}`}
                  onClick={() => isLoggedIn && handleRating(starNumber)}
                  style={{ cursor: isLoggedIn ? "pointer" : "not-allowed" }}
                >
                  ★
                </span>
              );
            })}
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
          {ingredientList.length ? (
            ingredientList.map((item, i) => <li key={i}>{item}</li>)
          ) : (
            <li>No ingredients listed</li>
          )}
        </ul>
      </section>

      {/* Instructions */}
      <section>
        <h2>Instructions</h2>
        <ol>
          {instructionSteps.length ? (
            instructionSteps.map((step, i) => <li key={i}>{step}.</li>)
          ) : (
            <li>No instructions listed</li>
          )}
        </ol>
      </section>

      {/* Review form */}
      {isLoggedIn && (
        <section className="review-section">
          <h2>Leave a Review</h2>
          <textarea
            placeholder="Share your thoughts..."
            value={reviewText}
            onChange={(e) => setReviewText(e.target.value)}
            rows="4"
          />
          <button onClick={submitReview}>Submit Review</button>
        </section>
      )}

      {/* Comments */}
      <section className="comments-list">
        <h3>Reviews</h3>
        {comments.length === 0 ? (
          <p>No reviews yet.</p>
        ) : (
          comments.map((c) => (
            <div key={c.id} className="comment-card">
              <p>{c.commentText}</p>
              <small>
                {c.username} •{" "}
                {c.createdAt ? new Date(c.createdAt).toLocaleDateString() : ""}
              </small>
            </div>
          ))
        )}
      </section>
    </main>
  );
};

export default RecipeDetailsPage;
