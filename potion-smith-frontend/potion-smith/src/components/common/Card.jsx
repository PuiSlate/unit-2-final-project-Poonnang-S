const Card = ({ children, clickable, onClick }) => {
  return (
    <div
      className={`card ${clickable ? 'clickable-card' : ''}`}
      onClick={onClick} // ✅ Attach onClick directly
    >
      {children}
    </div>
  );
};

export default Card;