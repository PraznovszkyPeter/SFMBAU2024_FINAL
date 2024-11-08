import {Link} from 'react-router-dom';

const NotFound = () => {

    document.title = "404";

    return (  
        <div className="notfound">
            <h1>Az oldal nem található!</h1>
            <Link to = "/">Vissza a főoldalra!</Link>
        </div>
    );
}
 
export default NotFound;