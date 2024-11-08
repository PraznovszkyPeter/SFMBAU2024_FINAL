import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

const Reservation = () => {

    document.title = "Reservation";

    const [panasz, setPanasz] = useState("");
    const [faj, setFaj] = useState("");
    const [idopont, setIdopont] = useState("");
    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();
        alert("Sikeres Foglalás!");
        const foglalas = {faj, panasz, idopont};
        history.push("/");
    }


    return ( 
        <div>
            <h2>Foglaljon időpontot még ma!</h2>
                <div>
                    <form>
                        <label>Panasz:
                            <input 
                                type="text"
                                value = {panasz}
                                onChange={(e) => setPanasz(e.target.value)}      
                            />
                        </label>
                    </form>
                </div>
                <div>
                    <form>
                        <label>Faj:
                            <input 
                                type="text"
                                value = {faj}
                                onChange={(e) => setFaj(e.target.value)}      
                            />
                        </label>
                    </form>
                </div>
                <div>
                <form>
                    <label>
                        Időpont:
                        <select>
                            <option value= "x">x</option>
                            <option value= "y">y</option>
                            <option value= "z">z</option>
                        </select>
                    </label>
                </form>
                    <div>
                        <button onClick={handleSubmit}>Foglalás</button>
                    </div>
                    
                </div>
        </div>
     );
}
 
export default Reservation;