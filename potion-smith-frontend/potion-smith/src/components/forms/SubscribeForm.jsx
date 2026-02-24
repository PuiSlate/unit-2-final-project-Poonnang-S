import { useState } from "react";
import Input from "./input/Input";
import Button from "./input/Button";

const SubscribeForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [subscribe, setSubscribe] = useState(true);
  const [message, setMessage] = useState("");
  const [messageType, setMessageType] = useState("success");

  const handleSubmit = (e) => {
    e.preventDefault();

    // basic validation for local logic only
    if (!name || !email) {
       setMessageType("error");
       setMessage("Please fill out all fields");
        return;
    }
   //simulate submission
    console.log({ name, email, subscribe });
    setMessageType("success");
    setMessage("Thank you for your subscription!");

    setName("");
    setEmail("");
    setSubscribe(true);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="container">
        <Input
          id="name"
          label="Name"
          type="text"
          value={name}
          handleChange={(e) => setName(e.target.value)}
          placeholder="Name"
          required
        />

        <Input
          id="email"
          label="Email address"
          type="email"
          value={email}
          handleChange={(e) => setEmail(e.target.value)}
          placeholder="Email address"
          required
        />

        <Input
          id="subscribe"
          label="Weekly Newsletter"
          type="checkbox"
          checked={subscribe}
          handleChange={(e) => setSubscribe(e.target.checked)}
        />
      </div>
      

      {/* conditional rendering with ternary operator*/}
      {message && (
        <div
          className={`message ${messageType === "error" ? "error" : "success"}`}
          style={{
            marginTop: "10px", textAlign: "center",
            color: messageType === "error" ? "red" : "green",
          }}
        >
          {message}
        </div>
      )}

      <div className="container">
       <Button
         id="subscribe"
         type="submit"
         label="Subscribe"
         classes="subscribe-btn"
       />
      </div>
    </form>
  );
};

export default SubscribeForm;