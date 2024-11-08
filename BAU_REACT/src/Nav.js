import {Link} from 'react-router-dom';

const Nav = () => {
    return (  
        <nav className='navbar'>
            <div className='linkek'>
                <p><b>B</b>AU</p>
                <Link to = '/' className = "link">Főoldal</Link>
                <Link to = '/docs'>Orvosok</Link>      
                <Link to = '/reservation'>Foglalás</Link>    
            </div>
            
        </nav>
    );
}
 
export default Nav;