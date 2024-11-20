import { useState } from "react";
import Reservation from "./Reservation";

const Home = () => {
    const [hoverText, setHoverText] = useState("");

    const handleHover = (text) => setHoverText(text);
    const clearHover = () => setHoverText("");

    return (
        <div className="home" style={{ textAlign: "center", padding: "1rem" }}>
            {/* Logo */}
            <img
                src={require("./log_00.png")}
                alt="logo"
                className="logo"
                id="pic"
                style={{
                    width: "325px",
                    marginBottom: "1.5rem",
                }}
            />

            {/* Bevezető szöveg */}
            <div>
                
            <h1
                 style={{color: "#12681a", marginBottom: "1rem", textShadow: "0 0 3px #fff, 0 0 5px #fff",  }}>
                        Üdvözlünk a BAU állatorvosi klinika weboldalán!
                        </h1>
                <p style={{ fontSize: "1.3rem", color: "#fff" }}>
                    Nálunk egyszerűen és gyorsan foglalhat időpontot szakértő orvosainkhoz!
                </p>
            </div>
           
        </div>
    );
};

export default Home;
