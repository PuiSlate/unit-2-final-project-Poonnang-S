import { useState } from 'react'
import { Routes, Route, Navigate } from 'react-router';
import Header from './components/Layout/Header'
import Footer from './components/Layout/Footer'
import RecipesPage from './components/pages/Recipes/RecipesPage'
import { mockRecipes } from "./test-data/mockRecipe";
import RecipeDetailsPage from './components/pages/Recipes/RecipeDetailsPage';
import HomePage from './components/pages/HomePage'
import AboutPage from './components/pages/AboutPage'
import SubscribePage from './components/pages/SubscribePage'
import ContactPage from './components/pages/ContactPage'
import AgeGate from './components/forms/AgeGate';
import LogInForm from './components/forms/LogInForm';
import UserDashboard from './components/Layout/UserDashboard';



function App() {
  const [searchQuery, setSearchQuery] = useState("");
  const [recipes, setRecipes] = useState(mockRecipes);
  const [ageVerified, setAgeVerified] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentUser, setCurrentUser] = useState(null); //store the user object returned from your backend login

  // AgeGate modal to appear before users access the homepage
  const handleAgeVerified = (verified) => {
    setAgeVerified(verified);
  };
  // If not verified, show the AgeGate first
  if (!ageVerified) {
    return <AgeGate onVerified={handleAgeVerified} />;
  }
  // Once verified, render the normal app
  return (
    <>
      <Header 
      searchQuery={searchQuery} 
      setSearchQuery={setSearchQuery}
      isLoggedIn={isLoggedIn}
      setIsLoggedIn={setIsLoggedIn}
      currentUser={currentUser}
      />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/recipes" element={<RecipesPage />} />
        <Route path="/recipes/:id" element={<RecipeDetailsPage recipes={recipes} setRecipes={setRecipes} />} />
        <Route path="/contact" element={<ContactPage />} />
        <Route path="/subscribe" element={<SubscribePage />} />
        <Route path="/login" element={<LogInForm setIsLoggedIn={setIsLoggedIn} setCurrentUser={setCurrentUser} />} />
        <Route path="*" element={<Navigate to="/" />} />
        <Route path="/dashboard" element={<UserDashboard user={currentUser} />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;