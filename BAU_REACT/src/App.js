import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './Home';
import Nav from './Nav';
import Docs from './Docs';
import Reservation from './Reservation';
import NotFound from './NotFound';

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route exact path = '/'>
            <Nav></Nav>
            <Home></Home>
          </Route>
          <Route exact path = '/docs'>
            <Nav></Nav>
            <Docs></Docs>
          </Route>
          <Route exact path = '/reservation'>
            <Nav></Nav>
            <Reservation></Reservation>
          </Route>
          <Route exact path = '*'>
            <NotFound></NotFound>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
