import { useState } from "react";
import Button from "./input/Button";

function AgeGate({ onVerified }) {
  const [denied, setDenied] = useState(false);

  const handleYes = () => {
    if (onVerified) onVerified(true);
  };

  const handleNo = () => {
    setDenied(true);
  };

  return (
    <div
      className="age-gate"
      style={{ color: "#ffffff", textAlign: "center", fontFamily: "'Smythe', system-ui" }}
    >
      {!denied ? (
        <>
          <h2>Welcome to Potion Smith!</h2>
          <h3>Are you 21 or older?</h3>

          <div className="age-buttons">
            <Button
              id="age-yes"
              type="button"
              label="Yes"
              classes="age-btn yes-btn"
              handleClick={handleYes}
            />

            <Button
              id="age-no"
              type="button"
              label="No"
              classes="age-btn no-btn"
              handleClick={handleNo}
            />
          </div>
        </>
      ) : (
        <p>Sorry, you must be 21 or older to continue on our website.</p>
      )}
    </div>
  );
}

export default AgeGate;
