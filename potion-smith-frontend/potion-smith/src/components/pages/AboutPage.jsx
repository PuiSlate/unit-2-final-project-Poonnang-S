
import { Link } from "react-router-dom";
import aboutPageImg from "../../assets/images/potion-smith-about-page.jpg"


const AboutPage = () => {
    return (
        <main>
            <div className="main-content">
            <h1>About Us</h1>
            <p>
            Hello! We are <Link to="/HomePage"><strong>Potion Smith</strong></Link>, where every drink is a crafted spell waiting to be brewed! Inspired by the worlds of 
            Dungeons & Dragons, Potion Smith transforms everyday ingredients into magical elixirs worthy of any adventuring
            party. Whether you're a seasoned alchemist or a novice spellcaster, you’ll find recipes tailored for every class,
            quest, and tavern mood. Discover shimmering healing tonics, fiery berserker brews, tranquil druid infusions, and 
            more—all designed to bring a touch of enchantment to your table. Raise your glass, traveler—your journey into the 
            art of potioncraft begins here.
            </p>
            </div>
            <img src={ aboutPageImg } width="100%" alt="Potion Smith About" />
        </main>
    )
}

export default AboutPage;