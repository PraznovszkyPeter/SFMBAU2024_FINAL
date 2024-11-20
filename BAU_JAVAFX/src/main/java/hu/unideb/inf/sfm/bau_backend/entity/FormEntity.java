package hu.unideb.inf.sfm.bau_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="form") //létrehoztuk a táblát a db-ben
@Builder //Builder pattern
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //egyesével növeli az Id-t
    private Integer id;
    private String panasz;
    private String faj;
    private LocalDate datum;
    private String idopont; //megnezni hogy jo e String kent hasznalni




}
