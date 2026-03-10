import Spacer from "../../common/spacer"
import Card from "../../common/Card"
import { recipeImages } from "../../../assets/images/images"

const RecipeCard = ({ recipe, onClick }) => {
   

    return (
    
        <Card clickable={true} onClick={onClick}>
            <img
                className="recipe-card-image"
                src={recipe.imageID ? recipeImages[recipe.imageID] : recipeImages["fallback.jpg"]}
                alt={`Image of ${recipe.name}`}
            />

            <div className="recipe-card-text">
                <h4>{recipe.name}</h4>
                <h5>{recipe.category}</h5>
            </div>

            <Spacer marginY="10px" />
        </Card>
    )
}

export default RecipeCard;