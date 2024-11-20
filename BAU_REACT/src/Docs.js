import './index';
import './docs.css'
import vet1 from './images/vet1.jpg';
import vet2 from './images/vet2.jpg';
import vet3 from './images/vet3.jpg';
import { useState } from "react";

    document.title = "Docs";

const Docs = () => {
    const [docs, setDocs] = useState([
        {nev: "Dr. Kis Béla", szakterulet: "sebész", telefonszam: "0620836293", email: "kisbela@gmail.com", id: 1, kep: vet1 },
        {nev: "Dr. Kovács Laura Szilvia", szakterulet: "altató",telefonszam: "0630834723", email: "kocsacslau@gmail.com", id: 2,  kep: vet2},
        {nev: "Dr. Nagy Eszter", szakterulet: "sebész",telefonszam: "0620345389", email: "nagye@gmail.com", id: 3,  kep: vet3}
    ]);

    return ( 
        <div className="orvosok">
        {docs.map((doc) => (
            <div key={doc.id}>
                <h2 className='orvos-neve'>{doc.nev}</h2>
                <p>Szakterület: {doc.szakterulet}</p>
                <p>Mobil: {doc.telefonszam}</p>
                <p>Email: {doc.email}</p>
                <img 
                    src={doc.kep} 
                    alt={`Dr. ${doc.nev}`} 
                    className="orvos-kep" 
                />
            </div>
        ))}
    </div>
    );
    
   
}
 
export default Docs;