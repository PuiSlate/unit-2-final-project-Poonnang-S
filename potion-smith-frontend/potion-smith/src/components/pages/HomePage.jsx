import { Link } from "react-router-dom";
import { useRef, useEffect, useState } from "react";
import { recipeImages } from "../../assets/images/images";

const HomePage = () => {
  const carouselRef = useRef(null);
  const [featuredRecipes, setFeaturedRecipes] = useState([]);

  // Fetch featured recipes from the backend
  const fetchFeatured = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/drinks"); // or a /featured endpoint if you have one
      const data = await res.json();
      // Optionally filter by weekly feature
      const featured = data.filter((r) => r.onWeeklyFeature === true);
      setFeaturedRecipes(featured);
    } catch (err) {
      console.error("Failed to fetch featured recipes:", err);
    }
  };

  useEffect(() => {
    fetchFeatured();
  }, []);

  useEffect(() => {
    const carousel = carouselRef.current;
    if (!carousel) return;

    //add a smooth scroll right by 1 item
    let scrollInterval = setInterval(() => {
      carousel.scrollBy({ left: 250, behavior: "smooth" });

      //if at the end, jump back to the start
      if (
        carousel.scrollLeft + carousel.offsetWidth >=
        carousel.scrollWidth - 5
      ) {
        setTimeout(() => {
          carousel.scrollTo({ left: 0, behavior: "smooth" });
        }, 600);
      }
    }, 2500);

    return () => clearInterval(scrollInterval);
  }, []);

  return (
    <main>
      <h1>Featured This Week</h1>

      <div className="homepage-carousel" ref={carouselRef}>
        {featuredRecipes.length === 0 ? (
          <p>Loading featured recipes...</p>
        ) : (
          featuredRecipes.map((recipe) => (
            <Link
              key={recipe.id}
              to={`/recipes/${recipe.id}`}
              className="carousel-item"
            >
              <img
                src={
                  recipeImages[recipe.imageId] || recipeImages["fallback.jpg"]
                }
                alt={recipe.drinkName}
                className="carousel-image"
              />
              <div className="carousel-name">{recipe.drinkName}</div>
            </Link>
          ))
        )}
      </div>
    </main>
  );
};

export default HomePage;
