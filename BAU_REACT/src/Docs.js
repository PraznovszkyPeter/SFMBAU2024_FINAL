import { useState } from "react";

    document.title = "Docs";

const Docs = () => {
    const [docs, setDocs] = useState([
        {nev: "Dr. Kis Béla", szakterulet: "sebész", telefonszam: "0620836293", email: "kisbela@gmail.com", id: 1},
        {nev: "Dr. Nagy József", szakterulet: "altató",telefonszam: "0630834723", email: "nagyjozsef@gmail.com", id: 2},
        {nev: "Dr. Gipsz Jakab", szakterulet: "sebész",telefonszam: "0620345389", email: "gipszj@gmail.com", id: 3}
    ]);

    return ( 
        <div className="orvosok">
            {docs.map((doc) => (
                <div key={doc.id}>
                    <h2>{doc.nev}</h2>
                    <p>Szakterület: {doc.szakterulet}</p>
                    <p>Mobil: {doc.telefonszam}</p>
                    <p>Email: {doc.email}</p>
                </div>
            ))}
        </div>
        );
    

    
}
 
export default Docs;