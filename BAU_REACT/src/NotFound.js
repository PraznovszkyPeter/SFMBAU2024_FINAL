import {Link} from 'react-router-dom';



const NotFound = () => {

    document.title = "404";

    return (  
        <div className="notfound">
            <p className='error'>Az oldal nem található!</p>
            <Link to = "/" className = "toHomePage">Vissza a főoldalra!</Link>
        </div>
    );
}
 
export default NotFound;