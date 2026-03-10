import { useState } from "react";

function LogInForm ({setIsLoggedIn}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmit(e){
    e.preventDefault();
    // Handle login logic here

    // Fake login (no backend yet) - just check if email and password are not empty
    if (email && password) {
        setIsLoggedIn(true);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
    <h2>Log In</h2>
      <input
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Email"
        required
      />
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
        required
      />
      <button type="submit">Log In</button>
    </form>
  );
};

export default LogInForm;
