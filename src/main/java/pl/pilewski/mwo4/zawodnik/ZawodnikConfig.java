package pl.pilewski.mwo4.zawodnik;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZawodnikConfig {

    @Bean
    CommandLineRunner commandLineRunner(IZawodnikRepository iZawodnikRepository) {
        return args -> {
            var z1 = new Zawodnik(1L, 2L, "A", "P", "Romania", LocalDate.of(1410, Month.JANUARY, 30), 77.7, 187.7);
            var z2 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(1999, Month.JANUARY, 30), 47.7, 147.7);
            var z3 = new Zawodnik(3L, "Aaaa", "Paaaa", "Poland", LocalDate.of(2002, Month.FEBRUARY, 22), 47.7, 147.7);
            var z4 = new Zawodnik(2L, "Aaaa", "Paaaa", "Hungary", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z5 = new Zawodnik(2L, "Aaaa", "Paaaa", "Ukraine", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z6 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z7 = new Zawodnik(4L, "Aaaa", "Paaaa", "Finland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z8 = new Zawodnik(2L, "Aaaa", "Paaaa", "USA", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z9 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z10 = new Zawodnik(3L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z11 = new Zawodnik(2L, "Aaaa", "Paaaa", "Mexico", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z12 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z13 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z14 = new Zawodnik(2L, "Aaaa", "Paaaa", "Spain", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z15 = new Zawodnik(2L, "Aaaa", "Paaaa", "DRC", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);
            var z16 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(999, Month.JANUARY, 30), 47.7, 147.7);

            iZawodnikRepository.saveAll(List.of(z1, z2, z3, z4, z5, z6, z7, z8, z9, z10,
                    z11, z12, z13, z14, z15, z16));
            // iZawodnikRepository.saveAll(List.of(z1, z2));
        };
    }
}
