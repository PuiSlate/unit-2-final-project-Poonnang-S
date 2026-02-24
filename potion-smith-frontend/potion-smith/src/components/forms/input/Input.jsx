

const Input = ({ id, label, type = "text", value, handleChange, placeholder, required, checked }) => {
  return (
    <div className={`input-group ${type === "checkbox" ? "checkbox-group" : ""}`}>
      {type !== "checkbox" && (
        <label htmlFor={id}>
          {label} {required && "*"}
        </label>
      )}
      <input
        id={id}
        type={type}
        value={value}
        onChange={handleChange}
        placeholder={placeholder}
        required={required}
        checked={type === "checkbox" ? checked : undefined}
      />
      {type === "checkbox" && <label htmlFor={id}>{label}</label>}
    </div>
  );
};

export default Input;