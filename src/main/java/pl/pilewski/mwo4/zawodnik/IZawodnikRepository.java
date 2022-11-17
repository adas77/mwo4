package pl.pilewski.mwo4.zawodnik;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IZawodnikRepository extends JpaRepository<Zawodnik, Long> {
    @Query("SELECT z FROM Zawodnik z WHERE z.country = ?1")
    Optional<List<Zawodnik>> findZawodnicyByCountry(String country);
}
