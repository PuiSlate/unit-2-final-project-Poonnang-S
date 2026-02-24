import Spacer from "../common/spacer";
import SubscribeForm from "../forms/SubscribeForm";

const SubscribePage = () => {
    return (
        <main>
            <div className="main-content">
            <h1>Subscribe</h1>
            <h2>Receive free weekly recipes & inspiration in your inbox!</h2>
            </div>

            <SubscribeForm />
           
        </main>
        
    )
}

export default SubscribePage;