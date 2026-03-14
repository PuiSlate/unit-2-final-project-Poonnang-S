import NavBar from "./NavBar";
import { useState } from "react";


const Header = ({ searchQuery, setSearchQuery, isLoggedIn, setIsLoggedIn, currentUser, setCurrentUser }) => {
  return (
    <header>
      <NavBar
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        isLoggedIn={isLoggedIn}
        setIsLoggedIn={setIsLoggedIn}
        currentUser={currentUser}
        setCurrentUser={setCurrentUser}
      />
    </header>
  );
};

export default Header;