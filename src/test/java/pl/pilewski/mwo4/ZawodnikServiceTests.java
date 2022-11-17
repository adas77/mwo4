package pl.pilewski.mwo4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.pilewski.mwo4.zawodnik.IZawodnikRepository;
import pl.pilewski.mwo4.zawodnik.Zawodnik;
import pl.pilewski.mwo4.zawodnik.ZawodnikService;

@ExtendWith(MockitoExtension.class)
public class ZawodnikServiceTests {

    @Mock
    private IZawodnikRepository iZawodnikRepository;
    private ZawodnikService zawodnikService;

    @BeforeEach
    public void setUp() {
        zawodnikService = new ZawodnikService(iZawodnikRepository);
    }

    @Test
    public void getZawodnicy_ForInvalidInput_ReturnNull() {
        when(iZawodnikRepository.findAll()).thenReturn(null);
        assertEquals(null, zawodnikService.getZawodnicy());
    }

    @Test
    public void getZawodnicy_ForValidInput_ReturnsCorrectCollection() {
        var z1 = new Zawodnik(1L, 2L, "A", "P", "Romania", LocalDate.of(1410, Month.JANUARY, 30), 77.7, 187.7);
        var z2 = new Zawodnik(2L, "Aaaa", "Paaaa", "Poland", LocalDate.of(1999, Month.JANUARY, 30), 47.7, 147.7);
        var z3 = new Zawodnik(3L, "Aaaa", "Paaaa", "Poland", LocalDate.of(2002, Month.FEBRUARY, 22), 47.7, 147.7);
        var zawodnicy = List.of(z1, z2, z3);

        when(iZawodnikRepository.findAll()).thenReturn(zawodnicy);
        assertEquals(zawodnicy, zawodnikService.getZawodnicy());
    }

    @Test
    public void getZawodnikById_ForValidId_ReturnsCorrectZawodnik() {
        var zawodnik = new Zawodnik();
        zawodnik.setName("Name");
        zawodnik.setSurname("SurName");

        when(iZawodnikRepository.findById(anyLong())).thenReturn(Optional.of(zawodnik));
        var zawodnikFromDb = zawodnikService.getZawodnikById(44L);

        assertEquals(zawodnik, zawodnikFromDb);
        assertEquals("", zawodnikFromDb.getName());
        assertEquals("SurName", zawodnikFromDb.getSurname());
    }

    @Test
    public void getZawodnikById_ForInvalidId_ReturnsNull() {
        var Id = 33L;

        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.empty());
        var zawodnikFromDb = zawodnikService.getZawodnikById(Id);

        assertEquals(null, zawodnikFromDb);
    }

    @Test
    public void addNewZawodnik_WithIdNotPresesntInDb_CallsSaveMethoOnce() {
        var zawodnik = new Zawodnik();
        zawodnikService.addNewZawodnik(zawodnik);
        verify(iZawodnikRepository, times(1)).save(zawodnik);
    }

    @Test
    public void addNewZawodnik_WithIdPresentInDb_ThrowsException() {
        var zawodnik = new Zawodnik();
        var Id = 4L;
        zawodnik.setIdZawodnik(Id);
        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.of(zawodnik));

        var exc = assertThrows(IllegalStateException.class, () -> {
            zawodnikService.addNewZawodnik(zawodnik);
        });
        assertEquals(String.format("Zawodnik with id=%d already exists", Id), exc.getMessage());

    }

    @Test
    public void deleteZawodnikById_WithIdNotPresentInDb_ThrowsException() {
        var Id = 4L;
        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.empty());

        var exc = assertThrows(IllegalStateException.class, () -> {
            zawodnikService.deleteZawodnikById(Id);
        });
        assertEquals(String.format("Zawodnik with id=%d does not exist", Id), exc.getMessage());
    }

    @Test
    public void deleteZawodnikById_WithIdPresentInDb_CallsDeleteMethodOnce() {
        var Id = 49L;
        var zawodnik = new Zawodnik();
        zawodnik.setIdZawodnik(Id);

        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.of(zawodnik));
        zawodnikService.deleteZawodnikById(Id);

        verify(iZawodnikRepository, times(1)).deleteById(Id);

    }

    @Test
    public void getZawodniksByCountryTest() {

    }

    @Test
    public void updateZawodnikById_WithIdNotPresentInDb_ThrowsException() {
        var Id = 4L;
        var zawodnik = new Zawodnik();
        zawodnik.setIdZawodnik(Id);
        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.empty());

        var exc = assertThrows(IllegalStateException.class, () -> {
            zawodnikService.updateZawodnikById(Id, zawodnik);
        });

        assertEquals(String.format("Zawodnik with id=%d does not exist", Id), exc.getMessage());
    }

    @Test
    public void updateZawodnikById_WithIdPresentInDb_CallsSaveMethodOnce() {
        var Id = 49L;
        var zawodnik = new Zawodnik();
        zawodnik.setIdZawodnik(Id);

        when(iZawodnikRepository.findById(Id)).thenReturn(Optional.of(zawodnik));
        zawodnikService.updateZawodnikById(Id, zawodnik);

        verify(iZawodnikRepository, times(1)).save(zawodnik);
    }
}
