import { useState } from "react";
import Reservation from "./Reservation";

const Home = () => {

    return ( 
        <div class="home">
            <img src={require("./baulog.jpg")} alt="logo" className="logo" id="pic"/>

            <div>
                <h2>Eddig beérkezett foglalásaink</h2>
            </div>

        </div>
     );
}
 
export default Home;