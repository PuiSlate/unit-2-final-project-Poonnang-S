import RecipeCard from "./RecipeCard";
import { useMemo } from "react";
import { useNavigate, useLocation } from "react-router-dom";

const RecipesPage = ({ recipes }) => {
  const navigate = useNavigate();
  const location = useLocation();

  // Read search query from URL
  const queryParams = new URLSearchParams(location.search);
  const searchQuery = queryParams.get("search")?.toLowerCase() || "";
  const selectedCategory = queryParams.get("category") || "";

  // Filtering recipes by search (name or category) and selectedCategory
  const filteredRecipes = useMemo(() => {
    return recipes.filter((recipe) => {
      const matchesSearch =
        searchQuery === "" ||
        recipe.name.toLowerCase().includes(searchQuery) ||
        recipe.category.toLowerCase().includes(searchQuery);

      const matchesCategory =
        selectedCategory === "" || recipe.category === selectedCategory;

     //Test casess for filtering logic
      // CASE 1: neither filter selected, show all
    if (searchQuery === "" && selectedCategory === "") {
      return true;
    }

    // CASE 2: search only
    if (searchQuery !== "" && selectedCategory === "") {
      return matchesSearch;
    }

    // CASE 3: category only
    if (searchQuery === "" && selectedCategory !== "") {
      return matchesCategory;
    }

    // CASE 4: BOTH filters, require BOTH to match
    return matchesSearch && matchesCategory;
  });

  }, [recipes, searchQuery, selectedCategory]);

  return (
    <main className="recipes-page">
      <h1>All Recipes</h1>
      <p>Welcome, brave adventurer, to the Potion Smith’s recipe compendium! Here you’ll find enchanted cocktails, 
      mystical mocktails, and legendary elixirs worthy of any dungeon master’s table. Mix your ingredients wisely,
    roll for flavor, and don’t forget to save your favorites before they vanish like a disappearing spell!</p>
      
{/* When change the category dropdown, the URL search clears */}
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
        <option value="">All Categories</option>
        <option value="Gin">Gin</option>
        <option value="Tequila">Tequila</option>
        <option value="Rum">Rum</option>
        <option value="Vodka">Vodka</option>
        <option value="Mocktail">Mocktail</option>
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
