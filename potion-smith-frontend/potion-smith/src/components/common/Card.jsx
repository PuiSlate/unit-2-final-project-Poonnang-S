const Card = ({ children, clickable, ...props }) => {
    return (
        <div
            className={`card ${clickable ? 'clickable-card' : ''}`}
            {...props}
            
        >
            {children}
        </div>
    );
};

export default Card;