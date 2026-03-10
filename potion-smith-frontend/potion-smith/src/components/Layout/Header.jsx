import NavBar from "./NavBar";
import { useState } from "react";


const Header = ({ searchQuery, setSearchQuery, isLoggedIn, setIsLoggedIn }) => {
  return (
    <header>
      <NavBar
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        isLoggedIn={isLoggedIn}
        setIsLoggedIn={setIsLoggedIn}
      />
    </header>
  );
};

export default Header;