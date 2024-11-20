import {Link} from 'react-router-dom';
import './navbar.css';

const Nav = () => {
    return (  
        <nav className='navbar'>
            <div className='linkek'>
                <p><b>B</b>AU</p>
                <Link to = '/' className = "link">Főoldal</Link>
                <Link to = '/docs' className = "link">Orvosok</Link>      
                <Link to = '/reservation' className = "link">Foglalás</Link>    
            </div>
            
        </nav>
    );
}
 
export default Nav;