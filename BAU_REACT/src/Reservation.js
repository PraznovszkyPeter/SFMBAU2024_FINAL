import './reservation.css';

import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import axios from 'axios';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const Reservation = () => {
    document.title = "Reservation";
    
    const [selectedDate, setSelectedDate] = useState(null);
    const history = useHistory();
    
    
    const [res, setRes] = useState({
        panasz: '',
        faj: '',
        gazdi_name: '',
        email: '',
        phone_num: '',
        datum: '',
        idopont: '',
    }) 

    const availableTimes = ["10:00", "14:00", "16:00"];
    const availableDates = []; 

    const [Dates, setDates] = useState([]);
   
    Dates.sort();
    const {panasz, faj, gazdi_name, email, phone_num, datum, idopont} = res;
    const handleInput = (event) => {
        setRes({...res, [event.target.name]: event.target.value});
    }

    const handleSubmit = (event) => {
        event.preventDefault();
       
        axios.post('http://localhost:8080/form', res)
            .then(response => console.log(response))
            .catch(err => console.log(err))

        console.log(res)
        history.push("/");
    };

    return (
        <div className="reservation-wrapper">
            <h2 className="reservation-heading">Foglaljon időpontot még ma!</h2>
            <form onSubmit={handleSubmit}>
                <label className="reservation-label">
                    Panasz:
                    <input type="text" onChange={handleInput} name="panasz" className="reservation-input"/>
                </label>

                <label className="reservation-label">
                    Gazdi neve:
                    <input type="text" onChange={handleInput} name="gazdi_name" className="reservation-input"/>
                </label>

                <label className="reservation-label">
                    Faj:
                    <input type="text" onChange={handleInput} name="faj" className="reservation-input"/>
                </label>
                
                <div className="reservation-field">
                    <label className="reservation-label">Dátum:</label>
                    <select onChange={handleInput} name = "datum" className="reservation-select">
                        <option value="">Szabad dátumok:</option>
                        {Dates.map((date, index) => (
                            <option key={index} value={date}>
                                {date}
                            </option>
                            ))}
                    </select>
                </div>
                
                
                <div className="reservation-field">
                    <label className="reservation-label">Időpont:</label>
                    <select onChange={handleInput} name = "idopont" className="reservation-select">
                        <option value="">Válasszon időpontot!</option>
                        {availableTimes.map((time, index) => (
                            <option key={index} value={time}>
                                {time}
                            </option>
                            ))}
                    </select>
                </div>

                <button className="reservation-button"> Foglalás </button>
            </form>
        </div>
    );
};

export default Reservation;
