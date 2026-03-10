import IconWithLabel from "../common/IconwithLabel";

const ContactPage = () => {
    return (
        <main>
            <div className="main-content">
            <h1>Contact Us</h1>
            <p>
             Have a question, request, or a potion idea to share? You can reach us at 
            <strong> contact@potionsmith.com</strong>. The Potion Smith team welcomes 
             messages from adventurers of all levels. Whether you've discovered a rare 
            ingredient, have feedback to enhance our brews, or wish to collaborate, 
            we’re here to help.
            </p>

            <p>
             Send a raven by email, connect through our social channels, or leave a note 
             at our virtual tavern counter. Every message is reviewed, and we’ll reply 
             as swiftly as our alchemists return from their latest quest.   
            </p>
            </div>


             <div className="main-content">
            <h1>Follow Us on Social Media</h1>
            <h2>Infusions of daily inspiration and recipe ideas, delivered straight to your feed.</h2>
            </div>
            
            <div id="contact-icons">
            <IconWithLabel id="email" classes="fa-solid fa-square-envelope">
                    contact@potionsmith.com
                </IconWithLabel>
                <IconWithLabel id="facebook" classes="fa-brands fa-square-facebook">
                    PotionSmith
                </IconWithLabel>
                <IconWithLabel id="instagram" classes="fa-brands fa-square-instagram">
                    @PotionSmith
                </IconWithLabel>
            </div>
        </main>
    )
}

export default ContactPage;