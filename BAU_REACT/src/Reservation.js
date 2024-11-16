import './reservation.css';

import { useState } from "react";
import { useHistory } from "react-router-dom";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const Reservation = () => {
    document.title = "Reservation";

    const [panasz, setPanasz] = useState("");
    const [faj, setFaj] = useState("");
    const [selectedDate, setSelectedDate] = useState(null); 
    const [selectedTime, setSelectedTime] = useState("");
    const history = useHistory();

       const availableTimes = ["10:00", "14:00", "16:00"];

    const handleSubmit = (event) => {
        event.preventDefault();
        if (!selectedDate || !selectedTime) {
            alert("Kérjük, válasszon dátumot és időpontot!");
            return;
        }
        alert(`Sikeres foglalás!\nDátum: ${selectedDate.toLocaleDateString()}, Időpont: ${selectedTime}`);
        const foglalas = { faj, panasz, date: selectedDate, time: selectedTime };
        history.push("/");
    };

    return (
        <div className="reservation-wrapper">
            <h2 className="reservation-heading">Foglaljon időpontot még ma!</h2>
            <form onSubmit={handleSubmit}>
                {/* Panasz mező */}
                <label className="reservation-label">
                    Panasz:
                    <input
                        type="text"
                        value={panasz}
                        onChange={(e) => setPanasz(e.target.value)}
                        className="reservation-input"
                    />
                </label>

                {/* Faj mező */}
                <label className="reservation-label">
                    Faj:
                    <input
                        type="text"
                        value={faj}
                        onChange={(e) => setFaj(e.target.value)}
                        className="reservation-input"
                    />
                </label>

                {/* Dátumválasztó */}
                <div className="reservation-field">
                    <label className="reservation-label">Dátum:</label>
                    <DatePicker
                        selected={selectedDate}
                        onChange={(date) => {
                            setSelectedDate(date); 
                            setSelectedTime("");
                        }}
                        minDate={new Date()}
                        dateFormat="yyyy-MM-dd"
                        className="reservation-input"
                    />
                </div>

                {/* Időpont kiválasztása */}
                {selectedDate && (
                    <div className="reservation-field">
                        <label className="reservation-label">Időpont:</label>
                        <select
                            value={selectedTime}
                            onChange={(e) => setSelectedTime(e.target.value)}
                            className="reservation-select"
                        >
                            <option value="">Válasszon időpontot</option>
                            {availableTimes.map((time, index) => (
                                <option key={index} value={time}>
                                    {time}
                                </option>
                            ))}
                        </select>
                    </div>
                )}

                {/* Foglalás gomb */}
                <button className="reservation-button">
                    Foglalás
                </button>
            </form>
        </div>
    );
};

export default Reservation;
