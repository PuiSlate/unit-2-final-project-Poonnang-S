
const Footer = () => {
    let thisYear = new Date().getFullYear();

    return (
        <footer>
            <div>&copy; {thisYear} Potion Smith</div>
        </footer>
    )
}

export default Footer;