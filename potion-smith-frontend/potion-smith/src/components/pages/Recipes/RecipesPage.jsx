import RecipeCard from "./RecipeCard";
import { useMemo, useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

const RecipesPage = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [recipes, setRecipes] = useState([]);
  const [spiritOptions, setSpiritOptions] = useState([]);

  // Fetch recipes on component mount
  useEffect(() => {
    fetch("http://localhost:8080/api/drinks")
      .then((res) => res.json())
      .then((data) => {
        // Map the drinks
        const mapped = data.map((drink) => ({
          id: drink.id,
          drinkName: drink.drinkName,
          ingredients: drink.drinkIngredients,
          instructions: drink.drinkInstructions,
          imageId: drink.imageId,
          onWeeklyFeature: drink.onWeeklyFeature,
          spiritCategory: drink.spiritCategory?.title || "",
          themeCategory: drink.themeCategory?.title || "",
        }));

        setRecipes(mapped);

      })
      .catch((err) => console.error("Error fetching recipes:", err));
  }, []);


  // Fetch distinct spirit categories
useEffect(() => {
  fetch("http://localhost:8080/api/spirit-categories")
    .then((res) => res.json())
    .then((data) => {
      // Extract titles from objects
      const titles = data.map((cat) => cat.title);
      setSpiritOptions(titles);
    })
    .catch((err) => console.error("Error fetching categories:", err));
}, []);

  // Read search query from URL
  const queryParams = new URLSearchParams(location.search);
  const searchQuery = queryParams.get("search")?.toLowerCase() || "";
  const selectedCategory = queryParams.get("category") || "";

  // Filtering recipes based on search query and selected category

  const filteredRecipes = useMemo(() => {
    return recipes.filter((recipe) => {
      const name = (recipe.drinkName || "").toLowerCase();
      const category = (recipe.spiritCategory || "").toLowerCase();

      const theme = (recipe.themeCategory || "").toLowerCase(); // Include theme category in search
      const matchesSearch =
        searchQuery === "" ||
        name.includes(searchQuery) ||
        category.includes(searchQuery) ||
        theme.includes(searchQuery);

      // category dropdown filter only checks spirit category, not theme category, to avoid confusion with the search filter which already includes theme category in its logic
      const matchesCategory =
        selectedCategory === "" ||
        (recipe.spiritCategory || "").toLowerCase() ===
          selectedCategory.toLowerCase();

      return matchesSearch && matchesCategory;
    });
  }, [recipes, searchQuery, selectedCategory]);

 

  return (
    <main className="recipes-page">
      <h1>All Recipes</h1>
      <p>
        Welcome, brave adventurer, to the Potion Smith’s recipe compendium! Here
        you’ll find enchanted cocktails, mystical mocktails, and legendary
        elixirs worthy of any dungeon master’s table. Mix your ingredients
        wisely, roll for flavor, and don’t forget to save your favorites before
        they vanish like a disappearing spell!
      </p>

      {/* When change the dropdown, the URL search clears */}
      <select
        value={selectedCategory}
        onChange={(e) => {
          const newCategory = e.target.value;
          const params = new URLSearchParams(location.search);

          if (newCategory === "") params.delete("category");
          else params.set("category", newCategory);

          navigate(`/recipes?${params.toString()}`);
        }}
      >
        {/* Category filter dropdown */}
        <option value="">All Categories</option>
        {spiritOptions.map((cat) => (
          <option key={cat} value={cat}>
            {cat}
          </option>
        ))}
      </select>

      <div className="recipes-grid">
        {filteredRecipes.map((recipe) => (
          <RecipeCard
            key={recipe.id}
            recipe={recipe}
            onClick={() => navigate(`/recipes/${recipe.id}`)}
          />
        ))}
      </div>
    </main>
  );
};

export default RecipesPage;
