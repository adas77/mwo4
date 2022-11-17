package pl.pilewski.mwo4.zawodnik;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "zaw")
@Getter
@Setter
@ToString
public class Zawodnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idZawodnik;
    private Long idTrener;
    private String name;
    private String surname;
    private String country;
    private LocalDate dob;
    private Double weight;
    private Double height;

    public Zawodnik() {
    }

    public Zawodnik(Long idZawodnik, Long idTrener, String name, String surname, String country, LocalDate dob,
            Double weight, Double height) {
        this.idZawodnik = idZawodnik;
        this.idTrener = idTrener;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public Zawodnik(Long idTrener, String name, String surname, String country, LocalDate dob,
            Double weight, Double height) {
        this.idTrener = idTrener;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

}
