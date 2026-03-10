import NavBar from "./NavBar";
import { useState } from "react";


const Header = () => {
  const [searchQuery, setSearchQuery] = useState("");
    return (
        <header>
            
            <NavBar
            searchQuery={searchQuery}
            setSearchQuery={setSearchQuery}
             />
            
        </header>
    )
}

export default Header;