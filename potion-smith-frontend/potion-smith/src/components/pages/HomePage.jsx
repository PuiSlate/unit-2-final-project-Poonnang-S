import { Link } from "react-router-dom";
import { useRef, useEffect } from "react";
import { recipeImages } from "../../assets/images/images";

const featuredRecipes = [
  { id: 8, name: "Ranger's Feral Senses", imageID: "rangers-feral-senses.jpg" },
  { id: 4, name: "The Hellfire Draught Margarita", imageID: "hellfire-draught-margarita.jpg" },
  { id: 2, name: "Bard's Song of Rest", imageID: "bard-song-of-rest.jpg" },
  { id: 1, name: "Klauth's Spell", imageID: "klauths-spell.jpg" },
  { id: 7, name: "Neverwinter Goblin's Grog", imageID: "never-winter-goblin-grog.jpg"}
];

const HomePage = () => {
    const carouselRef = useRef(null);

    useEffect(() => {
        const carousel = carouselRef.current;
  if (!carousel) return;

  //add a smooth scroll right by 1 item
  let scrollInterval = setInterval(() => {
    carousel.scrollBy({ left: 250, behavior: "smooth" });

    //if at the end, jump back to the start
    if (carousel.scrollLeft + carousel.offsetWidth >= carousel.scrollWidth - 5) {
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
        
        {featuredRecipes.map((recipe) => (
          <Link key={recipe.id} to={`/recipes/${recipe.id}`} className="carousel-item">
            <img
              src={recipeImages[recipe.imageID]}
              alt={recipe.name}
              className="carousel-image"
            />
            <div className="carousel-name">{recipe.name}</div>
          </Link>
        ))}
      </div>
    </main>
  );
};

export default HomePage;
